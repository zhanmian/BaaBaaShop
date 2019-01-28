package buy.baabaashop.service;

import buy.baabaashop.common.CommonException;
import buy.baabaashop.common.ResultData;
import buy.baabaashop.dao.CustomerDao;
import buy.baabaashop.entity.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
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
            usernameCookie.setMaxAge(24*60*60*30);
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
    @Transactional
    public ResultData addCartItem(HttpServletRequest request,
                            HttpServletResponse response,
                            CartItem cartItem) {
        ResultData resultData = new ResultData();
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            //如果对象中有Null值的就忽略，不转为Json
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            Cart cart = null;
            Cookie[] cookies = request.getCookies();

            if(cookies != null && cookies.length > 0){
                cart = getCartFromCookie(cookies, objectMapper);
            }
            //如果cart对象为null则新建一个对象
            if(cart == null){
                cart = new Cart();
            }
            //将当前商品添加进购物车商品列表
            if(cartItem.getSkuId() != null && cartItem.getQuantity() != null){
                cart.addItem(cartItem);
            }
            //如果用户没有登录的话就存入cookie
            HttpSession session = request.getSession();
            String username = (String)session.getAttribute("username");
            if(username == null || username.equals("")){
                //把对象转换为JSON字符串方便cookie存储
                String cartStr = objectMapper.writeValueAsString(cart);
                //存储cookie时如果没有对JSON字符串进行编码会出现严重错误：非法字符[34]
                Cookie cartCookie = new Cookie("cart", URLEncoder.encode(cartStr, "utf8"));
                cartCookie.setMaxAge(24*60*60*30);
                cartCookie.setPath("/");
                response.addCookie(cartCookie);
                resultData.setMessage("购物车添加成功");
            //如果用户登录了，那么将cookie中的购物车写入数据库
            }else{
                Integer customerId = customerDao.selectCustomerIdByUsername(username);
                //查找数据库中是否有同款商品，有就更新数量，没有就新增
                for(CartItem item : cart.getItems()){
                    item.setCustomerId(customerId);
                    CartItem dbCartItem = customerDao.checkCartItemBySkuId(item);
                    if(dbCartItem != null){
                        item.setId(dbCartItem.getId());
                        item.setQuantity(item.getQuantity() + dbCartItem.getQuantity());
                        customerDao.updateCartQuantity(item);
                    }else{
                        customerDao.addCart(item);
                    }
                }
                //把购物车cookie删除
                Cookie cookie = new Cookie("cart", null);
                cookie.setPath("/");
                cookie.setMaxAge(-0);
                response.addCookie(cookie);
                resultData.setMessage("购物车添加成功");
            }
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
    public String toCart(HttpServletRequest request, Model model) throws IOException {

        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");

        //如果用户没有登录从cookie获取购物车商品列表
        if(username == null || username.equals("")){
            ObjectMapper objectMapper = new ObjectMapper();
            //如果对象中有Null值的就忽略，不转为Json
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            Cart cart = null;
            Cookie[] cookies = request.getCookies();

            if(cookies != null && cookies.length > 0){
                cart = getCartFromCookie(cookies, objectMapper);
            }

            if(cart != null){
                for(CartItem item : cart.getItems()){
                    //遍历cookie，根据商品的SkuId查找商品详情并写入购物车
                    CartItem cartItem = customerDao.selectItemBySkuId(item);
                    item.setProductName(cartItem.getProductName());
                    item.setProductPicture(cartItem.getProductPicture());
                    item.setSkuCode(cartItem.getSkuCode());
                    item.setSkuPrice(cartItem.getSkuPrice());
                    item.setSpec1(cartItem.getSpec1());
                    item.setSpec2(cartItem.getSpec2());
                    item.setSpec3(cartItem.getSpec3());
                }

                model.addAttribute("cart", cart);
                model.addAttribute("totalPrice", cart.getTotalPrice());
            }

        //如果用户登录就从数据库购物车表获取商品列表
        }else{
            Integer customerId = customerDao.selectCustomerIdByUsername(username);
            Cart cart = new Cart();
            CartItem cartItem = new CartItem();
            cartItem.setCustomerId(customerId);
            //根据用户ID查找购物车表的商品列表
            List<CartItem> cartItems = customerDao.selectCartByCustomerId(cartItem);
            cart.setItems(cartItems);
            model.addAttribute("cart", cart);
            model.addAttribute("totalPrice", cart.getTotalPrice());
        }
        return "online_shop/client/cart";
    }

    @Override
    public ResultData updateQuantity(HttpServletRequest request,
                               HttpServletResponse response,
                               CartItem cartItem) throws IOException{
        ResultData resultData = new ResultData();
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        //如果没有登录就将购物车商品数量更新至cookie
        if(username == null || username.equals("")){
            ObjectMapper objectMapper = new ObjectMapper();
            //如果对象中有Null值的就忽略，不转为Json
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            Cart cart = null;
            Cookie[] cookies = request.getCookies();

            if(cookies != null && cookies.length > 0){
                cart = getCartFromCookie(cookies, objectMapper);
            }
            //查找出要更新数量的SKU商品的库存
            Integer skuStock = customerDao.selectItemBySkuId(cartItem).getSkuStock();
            if(cart != null){
                for(CartItem item : cart.getItems()){
                    //如果商品数量小于等于库存就更新否则不能更新并提示
                    if(item.getSkuId().equals(cartItem.getSkuId()) && cartItem.getQuantity() <= skuStock){
                        item.setQuantity(cartItem.getQuantity());
                    }else{
                        resultData.setCode(1);
                        resultData.setMessage("没有更多库存了");
                    }
                }
            }
            //把对象转换为JSON字符串方便cookie存储
            String cartStr = objectMapper.writeValueAsString(cart);
            //存储cookie时如果没有对JSON字符串进行编码会出现严重错误：非法字符[34]
            Cookie cartCookie = new Cookie("cart", URLEncoder.encode(cartStr, "utf8"));
            cartCookie.setMaxAge(24*60*60*30);
            cartCookie.setPath("/");
            response.addCookie(cartCookie);

        //如果用户登录了就将购物车商品数量更新至用户的数据库购物车表
        }else{
            //先根据username查找用户ID
            Integer customerId = customerDao.selectCustomerIdByUsername(username);
            cartItem.setCustomerId(customerId);
            //根据用户ID和SkuID查找到这个用户在数据库里的购物车表里要增加数量的商品
            CartItem dbItem = customerDao.checkCartItemBySkuId(cartItem);
            //该SKU的库存
            Integer skuStock = customerDao.selectItemBySkuId(cartItem).getSkuStock();
            //要更新的数量
            Integer quantity = cartItem.getQuantity();
            //如果要更新的数量小于等于库存就更新，否则就不能更新
            if(dbItem.getSkuId().equals(cartItem.getSkuId()) && quantity <= skuStock){
                dbItem.setQuantity(quantity);
                customerDao.updateCartQuantity(dbItem);
            }else{
                resultData.setCode(1);
                resultData.setMessage("没有更多库存了");
            }
        }
        return resultData;
    }

    public Cart getCartFromCookie(Cookie[] cookies, ObjectMapper objectMapper)throws IOException{
        Cart cart = null;
        for(Cookie cookie : cookies){
            //解码
            String name = URLDecoder.decode(cookie.getName(), "utf8");
            String value = URLDecoder.decode(cookie.getValue(), "utf8");
            //如果cookie的名称为cart就将Json转换为对象并赋给cart
            if(name.equals("cart")){
                cart = objectMapper.readValue(value, Cart.class);
                break;
            }
        }
        return cart;
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
