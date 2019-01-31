package buy.baabaashop.dao;

import buy.baabaashop.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerDao {

    Customer selectCustomerByUsername(Customer customer);
    void addCustomer(Customer customer);

    Product selectProductDetails(Product product);
    List<ProductSku> selectSkuByProductId(@Param("productId") Integer productId);
    List<ProductAttribute> selectProductAttribute(Product product);
    //查找手动添加的商品规格属性的值
    List<ProductAttribute> selectAddAttributeValue(Product product);
    //根据规格属性和商品ID查找出SKU，用于商品详情点击不同规格组合时显示不同的价格、库存等信息
    ProductSku selectSkuByAttributes(ProductSku productSku);
    //根据用户名查找用户ID
    Integer selectCustomerIdByUsername(@Param("username") String username);
    //添加购物车写入数据库表
    void addCart(CartItem cartItem);
    //更新购物车商品的数量
    void updateCartQuantity(CartItem cartItem);
    //根据SkuId和用户ID查找对应的购物车商品
    CartItem checkCartItemBySkuId(CartItem cartItem);
    //根据SkuId查找商品详情，主要用于查找库存数量
    CartItem selectItemBySkuId(CartItem cartItem);
    //根据用户ID查找该用户的购物车商品列表
    List<CartItem> selectCartByCustomerId(@Param("customerId") Integer customerId);
    void generateOrder(Order order);
    void insertOrderItem(OrderItem orderItem);
    void updateSkuStock(CartItem cartItem);
    void deleteCartByCustomerId(@Param("customerId") Integer customerId);
}
