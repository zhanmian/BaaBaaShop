package buy.baabaashop.controller;

import buy.baabaashop.common.ResultData;
import buy.baabaashop.configurations.AlipayConfig;
import buy.baabaashop.dao.CustomerDao;
import buy.baabaashop.entity.*;
import buy.baabaashop.service.CustomerServiceImp;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.apache.xalan.lib.Redirect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping(value = "/baabaa")
public class CustomerController {

    @Value("${baseUrl}")
    private String baseUrl;

    @ModelAttribute
    public void populateModel(Model model) {
        model.addAttribute("baseUrl", baseUrl);
    }

    @Resource
    private CustomerServiceImp customerServiceImp;

    @Resource
    private CustomerDao customerDao;

    @RequestMapping(value = "/to_sign_up")
    public String toRegister(){
        return "online_shop/client/online_shop_register";
    }

    @RequestMapping(value = "/register")
    @ResponseBody
    public ResultData register(HttpServletRequest request, Customer customer){
        return customerServiceImp.register(customer);
    }

    @RequestMapping(value = "/to_login")
    public String toLogin(){
        return "online_shop/client/online_shop_login";
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public ResultData login(HttpServletRequest request,
                            HttpServletResponse response,
                            Customer customer){
        return customerServiceImp.login(request, response, customer);
    }

    @RequestMapping(value = "/home")
    public String toHome(){
        return "online_shop/client/home";
    }

    @RequestMapping(value = "/product_details")
    public String toProductDetails(HttpServletRequest request,
                                   Product product,
                                   Model model){
        product.setId(1);
        model.addAttribute("product", customerDao.selectProductDetails(product));
        String attributeList = customerServiceImp.selectProductAttribute(product);
        model.addAttribute("attributeList", attributeList);
        model.addAttribute("productId", product.getId());
        return "online_shop/client/product_details";
    }

    @RequestMapping(value = "get_sku_details")
    @ResponseBody
    //根据规格属性和商品ID查找匹配的sku以实现点击后动态更改价格等信息
    public ProductSku getProductDetails(@RequestBody ProductSku productSku){
        return customerDao.selectSkuByAttributes(productSku);
    }

    @RequestMapping(value = "/add_cart")
    @ResponseBody
    public ResultData addCart(HttpServletRequest request, CartItem cartItem,
                              HttpServletResponse response, Model model){
        return customerServiceImp.addCartItem(request, response, cartItem);
    }

    @RequestMapping(value = "/cart")
    public String toCart(HttpServletRequest request, Model model)throws IOException{
        return customerServiceImp.toCart(request, model);
    }

    @RequestMapping(value = "/check_stock")
    @ResponseBody
    public Integer checkStock(HttpServletRequest request, CartItem cartItem){
        return customerDao.selectItemBySkuId(cartItem).getSkuStock();
    }

    @RequestMapping(value = "update_quantity")
    @ResponseBody
    public ResultData updateQuantity(HttpServletRequest request,
                                     HttpServletResponse response,
                                     CartItem cartItem) throws IOException{
        return customerServiceImp.updateQuantity(request, response, cartItem);
    }

    @RequestMapping(value = "checkout")
    public String toCheckout(HttpServletRequest request, Model model){
        return customerServiceImp.checkOut(request, model);
    }

    @RequestMapping(value = "generate_order")
//    @ResponseBody
    public String generateOrder(HttpServletRequest request, Order orderParam,
                                HttpServletResponse response, RedirectAttributes attributes){
        return customerServiceImp.generateOrder(request, response, orderParam, attributes);
    }

    @RequestMapping(value = "alipay")
    @ResponseBody
    public String toPayPage(@ModelAttribute Order order) throws Exception{

        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
                AlipayConfig.merchant_private_key, "json", AlipayConfig.charset,
                AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = order.getOrderCode();
        //付款金额，必填
        String total_amount = String.valueOf(order.getTotalAmount());
        //订单名称，必填
        String subject = "BaaBaa Shop 商城订单";
        //商品描述，可空
        String body = " ";

        //该笔订单允许的最晚付款时间，逾期将关闭交易。
        // 取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "1c";

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\""+ timeout_express +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //返回请求
        return alipayClient.pageExecute(alipayRequest).getBody();
    }

    @RequestMapping(value = "alipay_return")
    public String alipayReturn(HttpServletRequest request,
                             HttpServletResponse response,
                             Model model)throws Exception{

        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key,
                AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            model.addAttribute("orderCode", out_trade_no);
            model.addAttribute("alipayTradeNo", trade_no);
            model.addAttribute("totalAmount", total_amount);

        }else {
            System.out.println("验签失败");
        }
        //——请在这里编写您的程序（以上代码仅作参考）——
        return "/online_shop/client/alipay_return";
    }

    //从request获取JSON
    public static String getRequestBodyAsString(HttpServletRequest request) throws Exception {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder("");
        try {
            br = request.getReader();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            br.close();
        } catch (IOException e) {
            throw e;
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String s = sb.toString();
        System.out.println("请求数据：" + s);
        return s;
    }

}
