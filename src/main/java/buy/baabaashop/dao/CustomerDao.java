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
    List<ProductAttribute> selectAddAttributeValue(Product product);
    ProductSku selectSkuByAttributes(ProductSku productSku);
    Integer selectCustomerIdByUsername(@Param("username") String username);
    void addCart(CartItem cartItem);
    List<CartItem> selectCartByCustomerId(@Param("customerId") Integer customerId);
    void updateCartQuantity(CartItem cartItem);
    CartItem checkCartItemBySkuId(CartItem cartItem);
}
