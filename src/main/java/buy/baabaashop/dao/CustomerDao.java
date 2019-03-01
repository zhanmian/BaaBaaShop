package buy.baabaashop.dao;

import buy.baabaashop.common.PaginationRequestParam;
import buy.baabaashop.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerDao {

    //查找用户名和密码
    Customer selectCustomerByUsername(Customer customer);

    //添加用户
    void addCustomer(Customer customer);

    //查找首页推荐商品
    List<Product> selectRecommendProduct();

    //查找所有的商品分类
    List<ProductCategory> selectAllProductCategory();

    //查找某一分类下的商品
    List<Product> selectProductByCategoryId(PaginationRequestParam param);

    //查找某一分类的商品数量
    Integer selectTotalRecordOfProductByCategory(@Param("categoryId") Integer categoryId);

    //查找分类名称
    String selectCategoryName(@Param("categoryId") Integer categoryId);

    //查找商品详情
    Product selectProductDetails(Product product);

    List<ProductSku> selectSkuByProductId(@Param("productId") Integer productId);

    //根据商品ID查找商品的规格属性
    List<ProductAttribute> selectProductAttribute(Product product);

    //查找手动添加的商品规格属性的值
    List<ProductAttribute> selectAddAttributeValue(Product product);

    //根据规格属性和商品ID查找出SKU，用于商品详情点击不同规格组合时显示不同的价格、库存等信息
    ProductSku selectSkuByAttributes(ProductSku productSku);

    //根据用户名查找用户ID
    Integer selectCustomerIdByUsername(@Param("username") String username);

    //添加购物车
    void addCart(CartItem cartItem);

    //更新购物车商品的数量
    void updateCartQuantity(CartItem cartItem);

    //根据SkuId和用户ID查找对应的购物车商品
    CartItem checkCartItemBySkuId(CartItem cartItem);

    //根据SkuId查找商品详情，主要用于查找库存数量
    CartItem selectItemBySkuId(CartItem cartItem);

    //根据用户ID查找该用户的购物车商品列表
    List<CartItem> selectCartByCustomerId(@Param("customerId") Integer customerId);

    //生成订单
    void generateOrder(Order order);

    //订单包含的商品
    void insertOrderItem(OrderItem orderItem);

    //更新SKU库存
    void updateSkuStock(CartItem cartItem);

    //生成订单后删除购物车
    void deleteCartByCustomerId(@Param("customerId") Integer customerId);
}
