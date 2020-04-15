package buy.baabaashop.service;


import buy.baabaashop.common.PaginationResultData;
import buy.baabaashop.common.Result;
import buy.baabaashop.entity.*;
import buy.baabaashop.entity.client.*;

import java.util.List;
import java.util.Map;

public interface CustomerService {

    /**
     * 注册
     */
    Result register(Customer customer);

    /**
     * 登录
     */
    Result login(Customer customer);

    /**
     * 根据用户ID查找收货地址
     */
    List<Address> selectAddress(Integer userId);

    /**
     * 获取首页推荐商品列表
     */
    List<Product> selectRecommendProduct();

    /**
     * 获取所有的分类列表
     */
    List<ProductCategory> getAllProductCategory();

    /**
     * 根据分类获取商品
     */
    PaginationResultData<Product> getProductByCategory(ProductCategoryParam param);

    PaginationResultData<OrderResult> getOrderListByUser(OrderRequestParam param);

    PaginationResultData<Product> getProductBySearch(ProductSearchParam param);

    /**
     * 加载商品详情时获取规格属性
     */
    List<ProductAttribute> selectProductAttribute(Integer id);

    /**
     * 获取前端本地购物车中的商品的详情
     */
    Map getCartItemsDetails(List<CartItem> cartItems);

    /**
     * 获取支付宝返回的Form表单
     */
    String aliPay(OrderParam orderParam) throws Exception;

    /**
     * 生成订单并前往支付
     */
    Result pay(OrderParam orderParam);

    Result updateOrder(OrderParam param);
}
