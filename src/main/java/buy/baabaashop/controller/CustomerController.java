package buy.baabaashop.controller;

import buy.baabaashop.common.ResultData;
import buy.baabaashop.dao.CustomerDao;
import buy.baabaashop.entity.*;
import buy.baabaashop.service.CustomerServiceImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

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

    @RequestMapping(value = "checkout")
    public String toCheckout(){
        return "online_shop/client/checkout";
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
