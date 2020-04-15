package buy.baabaashop.controller;

import buy.baabaashop.common.Result;
import buy.baabaashop.configurations.AlipayConfig;
import buy.baabaashop.dao.CustomerDao;
import buy.baabaashop.entity.*;
import buy.baabaashop.entity.client.*;
import buy.baabaashop.service.CustomerService;
import com.alipay.api.internal.util.AlipaySignature;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/baabaa")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @Resource
    private CustomerDao customerDao;

    //注册
    @RequestMapping(value = "/register")
    @ResponseBody
    public Result register(@RequestBody Customer customer) {
        return customerService.register(customer);
    }

    //登录，返回Token
    @RequestMapping(value = "/login")
    @ResponseBody
    public Result login(@RequestBody Customer customer) {
        return customerService.login(customer);
    }

    /**
     * 获取用户的收货地址列表
     */
    @RequestMapping("/getAddress")
    @ResponseBody
    public Result getAddress(@RequestParam Integer userId) {
        return Result.success(customerService.selectAddress(userId));
    }

    /**
     * 添加收货地址
     */
    @RequestMapping("/addAddress")
    @ResponseBody
    public Result addAddress(@RequestBody Address address) {
        customerDao.addAddress(address);
        return Result.success();
    }

    /**
     * 根据分类获取商品列表（分页）
     */
    @RequestMapping(value = "/getProductByCategory")
    @ResponseBody
    public Result getProductByCategory(@RequestBody ProductCategoryParam param) {
        return Result.success(customerService.getProductByCategory(param));
    }

    //获取全部分类
    @RequestMapping("/getCategoryList")
    @ResponseBody
    public Result getCategoryList() {
        return Result.success(customerService.getAllProductCategory());
    }

    /**
     * 获取用户的订单列表
     */
    @RequestMapping("/getOrderListByUser")
    @ResponseBody
    public Result getOrderListByUser(@RequestBody OrderRequestParam param) {
        return Result.success(customerService.getOrderListByUser(param));
    }

    //首页推荐商品列表
    @RequestMapping(value = "/getRecommendProducts")
    @ResponseBody
    public Result getRecommendProducts() {
        return Result.success(customerService.selectRecommendProduct());
    }

    /**
     * 搜索商品（分页）
     */
    @RequestMapping("/searchProduct")
    @ResponseBody
    public Result searchProduct(@RequestBody ProductSearchParam param) {
        return Result.success(customerService.getProductBySearch(param));
    }

    //根据 productId 获取商品详情
    @RequestMapping("/getProductDetails")
    @ResponseBody
    public Result toProductDetails(@RequestParam("id") int id) {
        Map<String, Object> data = new HashMap<>();
        data.put("product", customerDao.selectProductDetails(id));
        data.put("attributeList", customerService.selectProductAttribute(id));
        return Result.success(data);
    }

    //根据规格属性和商品ID查找匹配的sku以实现点击后动态更改价格等信息
    @RequestMapping("/getSkuDetails")
    @ResponseBody
    public Result getProductDetails(@RequestBody ProductSku productSku) {
        ProductSku sku = customerDao.selectSkuByAttributes(productSku);
        if (sku != null) {
            return Result.success(sku);
        } else {
            return Result.failed("请求失败，商品信息出错");
        }
    }

    //获取前端本地购物车中的商品详情列表
    @RequestMapping("/getCartItemsDetails")
    @ResponseBody
    public Result getCartItemsDetails(@RequestBody List<CartItem> cartItems) {
        return Result.success(customerService.getCartItemsDetails(cartItems));
    }

    /**
     * 支付结算
     */
    @RequestMapping("/pay")
    @ResponseBody
    public Result pay(@RequestBody OrderParam orderParam) {
        return customerService.pay(orderParam);
    }

    /**
     * 支付完成后修改订单数据
     */
    @RequestMapping("/updateOrder")
    @ResponseBody
    public Result updateOrder(@RequestBody OrderParam param) {
        return customerService.updateOrder(param);
    }

    /**
     * 用于支付宝回调，用于以前的JSP，现在前端是获取路由参数
     */
    @RequestMapping("/alipay_return")
    @ResponseBody
    public Result alipayReturn(HttpServletRequest request) throws Exception {

        //获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
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

        if (signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

            Map<String, Object> data = new HashMap<>();
            data.put("orderCode", out_trade_no);
            data.put("alipayTradeNo", trade_no);
            data.put("totalAmount", total_amount);
            return Result.success(data);
        } else {
            return Result.failed("验签失败");
        }
    }
}
