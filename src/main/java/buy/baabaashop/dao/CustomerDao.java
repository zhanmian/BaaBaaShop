package buy.baabaashop.dao;

import buy.baabaashop.entity.*;
import buy.baabaashop.entity.client.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerDao {

    //查找用户名和密码
    Customer selectCustomerByUsername(Customer customer);

    //添加用户
    void addCustomer(Customer customer);



    //查找用户的收货地址
    List<Address> selectAddress(@Param("userId") Integer userId);

    //新增收货地址
    void addAddress(Address address);



    //查找用户的订单列表
    List<OrderResult> selectOrderByUserId(OrderRequestParam param);

    //查找用户的订单所拥有的商品列表
    List<OrderItem> selectOrderItemByOrderId(@Param("userId")Integer userId);

    //查找用户的订单列表的总数量
    Integer selectTotalRecordForOrderList(OrderRequestParam param);



    //搜索商品
    List<Product> searchProduct(ProductSearchParam productSearchParam);

    //搜索商品结果的总数量
    Integer selectTotalRecordForSearch(ProductSearchParam productSearchParam);



    //查找首页推荐商品
    List<Product> selectRecommendProduct();



    //查找所有的商品分类
    List<ProductCategory> selectAllProductCategory();

    //查找某一分类下的商品
    List<Product> selectProductByCategoryId(ProductCategoryParam param);

    //查找某一分类的商品数量
    Integer selectTotalRecordOfProductByCategory(ProductCategoryParam param);



    //查找商品详情
    Product selectProductDetails(@Param("id") Integer id);



    List<ProductSku> selectSkuByProductId(@Param("productId") Integer productId);

    //根据商品ID查找商品的规格属性
    List<ProductAttribute> selectProductAttribute(@Param("id") Integer id);

    //查找手动添加的商品规格属性的值
    List<ProductAttribute> selectAddAttributeValue(@Param("id") Integer id);

    //根据规格属性和商品ID查找出SKU，用于商品详情点击不同规格组合时显示不同的价格、库存等信息
    ProductSku selectSkuByAttributes(ProductSku productSku);

    //根据SkuId查找商品详情，主要用于查找库存数量
    List<CartItem> selectItemBySkuId(List<CartItem> cartItems);



    //写入订单
    void addOrder(OrderParam order);

    //写入订单包含的商品
    void insertOrderItem(OrderParam orderParam);

    //更新SKU库存
    void updateSkuStock(List<CartItem> cartItems);

    void updateOrder(OrderParam orderParam);

}
