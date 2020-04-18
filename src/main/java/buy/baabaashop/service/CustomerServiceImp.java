package buy.baabaashop.service;

import buy.baabaashop.common.*;
import buy.baabaashop.configurations.AlipayConfig;
import buy.baabaashop.dao.CustomerDao;
import buy.baabaashop.entity.*;
import buy.baabaashop.entity.client.*;
import buy.baabaashop.utils.JwtTokenUtil;
import buy.baabaashop.utils.Utils;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImp implements CustomerService {

    @Resource
    private CustomerDao customerDao;

    @Resource
    private CustomerService customerService;

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Result register(Customer customer){
        String password = Utils.md5Password(customer.getPassword());
        customer.setPassword(password);
        try{
            customerDao.addCustomer(customer);
            return Result.success(null, "注册成功，前往登录页面");
        }catch(Exception e){
            e.printStackTrace();
            return Result.failed(e.getMessage());
        }
    }

    @Override
    public Result login(Customer customer){
        Customer user = customerDao.selectCustomerByUsername(customer);
        String password = user.getPassword();

        if (Utils.md5Password(customer.getPassword()).equals(password)) {
            String token = JwtTokenUtil.generateToken(customer.getUsername());

            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("userInfo", user);

            return Result.success(data);
        } else {
            return Result.failed("密码或用户名输入错误");
        }
    }

    @Override
    public List<Address> selectAddress(Integer userId) {
        return customerDao.selectAddress(userId);
    }

    @Override
    public List<Product> selectRecommendProduct(){
        return customerDao.selectRecommendProduct();
    }

    @Override
    public List<ProductCategory> getAllProductCategory(){
        return customerDao.selectAllProductCategory();
    }

    @Override
    public PaginationResultData<Product> getProductByCategory(ProductCategoryParam param) {
        PaginationResultData<Product> resultData = new PaginationResultData<>();
        Integer totalRecord = customerDao.selectTotalRecordOfProductByCategory(param);
        resultData.calc(param.getPage(), param.getPageSize(), totalRecord);
        List<Product> list = customerDao.selectProductByCategoryId(param);
        resultData.setList(list);
        return resultData;
    }

    @Override
    public PaginationResultData<OrderResult> getOrderListByUser(OrderRequestParam param) {
        PaginationResultData<OrderResult> resultData = new PaginationResultData<>();
        Integer totalRecord = customerDao.selectTotalRecordForOrderList(param);
        resultData.calc(param.getPage(), param.getPageSize(), totalRecord);
        List<OrderResult> list = customerDao.selectOrderByUserId(param);
        resultData.setList(list);
        return resultData;
    }

    @Override
    public PaginationResultData<Product> getProductBySearch(ProductSearchParam param) {
        PaginationResultData<Product> resultData = new PaginationResultData<>();
        Integer totalRecord = customerDao.selectTotalRecordForSearch(param);
        resultData.calc(param.getPage(), param.getPageSize(), totalRecord);
        List<Product> list = customerDao.searchProduct(param);
        resultData.setList(list);
        return resultData;
    }


    @Override
    public List<ProductAttribute> selectProductAttribute(Integer id) {
        //查找出该商品对应的规格属性列表
        List<ProductAttribute> attributeList = customerDao.selectProductAttribute(id);
        //查找出该商品对应的手动输入的规格属性列表
        List<ProductAttribute> addAttributeValueList = customerDao.selectAddAttributeValue(id);

        for(ProductAttribute p : attributeList){
            for(ProductAttribute a : addAttributeValueList){
                //把手动输入的规格属性值放入
                if(p.getInputStatus() == 0 && p.getId().equals(a.getId())){
                    p.setInputList(a.getValue());
                }
            }
            p.setAttributeValues(
                    Arrays.asList(p.getInputList().split(",")).stream().map(s -> String.format(s.trim())).collect(Collectors.toList()));
        }
        return attributeList;
    }

    @Override
    public Map getCartItemsDetails(List<CartItem> cartItems) {
        List<CartItem> itemList = customerDao.selectItemBySkuId(cartItems);
        float subTotalAmount = 0;
        for (CartItem item : itemList) {
            for (CartItem cartItem : cartItems) {
                if (item.getSkuId().equals(cartItem.getSkuId())) {
                    item.setQuantity(cartItem.getQuantity());
                    item.setItemTotalPrice(cartItem.getQuantity() * item.getSkuPrice());
                    subTotalAmount = subTotalAmount + item.getItemTotalPrice();
                }
            }
        }
        Map<String, Object> data = new HashMap<>();
        data.put("cartItems", itemList);
        data.put("subTotalAmount", subTotalAmount);
        return data;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Result pay(OrderParam orderParam) {
        if (orderParam.getAddress() == null) {
            return Result.failed("地址为空");
        }
        //第一步：查库存
        List<CartItem> cartItems = customerDao.selectItemBySkuId(orderParam.getCartItems());
        for (CartItem cartItem : cartItems) {
            if (cartItem.getSkuStock() <= 0) {
                return Result.failed("很抱歉，这件商品已经售罄：" + cartItem.getProductName() + "（" + cartItem.getSpec1() + " / " + cartItem.getSpec2() + "）");
            }
        }
        //第二步：创建订单
        String orderCode = Utils.generateOrderSn(orderParam);
        orderParam.setOrderCode(orderCode);
        orderParam.setStatus(0);
        orderParam.setConfirmStatus(0);
        customerDao.addOrder(orderParam);
        customerDao.insertOrderItem(orderParam);
        //减库存
        customerDao.updateSkuStock(orderParam.getCartItems());
        //第三步：支付
        String result = "";
        try {
            if (orderParam.getPayType() == 1) { // 1 代表支付宝
                //前端在页面显示这个支付宝返回的form表单，并跳转到支付页面
                result = customerService.aliPay(orderParam);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failed(e.getMessage());
        }
        return Result.success(result);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Result updateOrder(OrderParam param) {
        try {
            customerDao.updateOrder(param);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failed(e.getMessage());
        }
    }

    @Override
    public String aliPay(OrderParam orderParam) throws Exception {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
                AlipayConfig.merchant_private_key, "json", AlipayConfig.charset,
                AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = orderParam.getOrderCode();
        //付款金额，必填
        String total_amount = String.valueOf(orderParam.getTotalAmount());
        //订单名称，必填
        String subject = "BaaBaa Shop 商城订单";
        //商品描述，可空
        String body = " ";

        //该笔订单允许的最晚付款时间，逾期将关闭交易。
        // 取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "1c";

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"timeout_express\":\"" + timeout_express + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //返回请求
        return alipayClient.pageExecute(alipayRequest).getBody();
    }
}
