package buy.baabaashop.service;

import buy.baabaashop.common.CommonException;
import buy.baabaashop.common.ResultData;
import buy.baabaashop.dao.CustomerDao;
import buy.baabaashop.entity.CartItem;
import buy.baabaashop.entity.Customer;
import buy.baabaashop.entity.Product;
import buy.baabaashop.entity.ProductAttribute;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {

    @Resource
    private CustomerDao customerDao;

    @Override
    @Transactional
    public ResultData register(Customer customer){
        ResultData resultData = new ResultData();
        String password = md5Password(customer.getPassword());
        customer.setPassword(password);
        try{
            customerDao.addCustomer(customer);
        }catch(CommonException c){
            c.printStackTrace();
            throw new CommonException(c.getCode(), c.getMessage());
        }catch(Exception e){
            e.printStackTrace();
            throw new CommonException();
        }
        return resultData;
    }

    @Override
    public ResultData login(HttpServletRequest request,
                            HttpServletResponse response,
                            Customer customer){
        ResultData resultData = new ResultData();
        String password = customerDao.selectCustomerByUsername(customer).getPassword();

        if (md5Password(customer.getPassword()).equals(password)) {
            resultData.setMessage("登录成功");
            resultData.setCode(1);

            HttpSession session = request.getSession();
            session.setAttribute("username", customer.getUsername());
            session.setAttribute("password", password);

            Cookie usernameCookie = new Cookie("username", customer.getUsername());
            usernameCookie.setMaxAge(7200);
            usernameCookie.setPath("/");
            response.addCookie(usernameCookie);

            System.out.println("外部的SessionId:" + session.getId());
        } else {
            resultData.setMessage("密码或用户名输入错误");
            resultData.setCode(0);
        }
        return resultData;
    }

    @Override
    public String selectProductAttribute(Product product){
        //查找出该商品对应的规格属性列表
        List<ProductAttribute> attributeList = customerDao.selectProductAttribute(product);
        //查找出该商品对应的手动输入的规格属性列表
        List<ProductAttribute> addAttributeValueList = customerDao.selectAddAttributeValue(product);
        //创建一个JSON数组节点
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode arrayNode = objectMapper.createArrayNode();

        for(ProductAttribute p : attributeList){
            for(ProductAttribute a : addAttributeValueList){
                //把手动输入的规格属性值放入
                if(p.getInputStatus() == 0 && p.getId().equals(a.getId())){
                    p.setInputList(a.getValue());
                }
            }
            //创建一个JSON对象节点并把它添加进之前创建的JSON数组节点中
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("name", p.getAttributeName());
            objectNode.put("value", p.getInputList());
            arrayNode.add(objectNode);
        }
        return arrayNode.toString();
    }

    @Override
    public ResultData addCartItem(HttpServletRequest request,
                            HttpServletResponse response,
                            CartItem cartItem) {
        ResultData resultData = new ResultData();
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            //把对象转换为JSON字符串方便cookie存储
            String cartItemStr = objectMapper.writeValueAsString(cartItem);
            //存储cookie时如果没有对JSON字符串进行编码会出现严重错误：非法字符[34]
            Cookie cartCookie = new Cookie("cart", URLEncoder.encode(cartItemStr, "utf-8"));
            //存活时间设为-0实现永久存储
            cartCookie.setMaxAge(-0);
            cartCookie.setPath("/");
            response.addCookie(cartCookie);
            resultData.setMessage("购物车添加成功");

        }catch(Exception e){
            e.printStackTrace();
            resultData.setMessage("购物车添加失败");
        }
        return resultData;
    }

    public static String md5Password(String password) {
        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }
            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
