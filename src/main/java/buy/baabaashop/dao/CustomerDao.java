package buy.baabaashop.dao;

import buy.baabaashop.entity.Customer;
import buy.baabaashop.entity.Product;
import buy.baabaashop.entity.ProductAttribute;
import buy.baabaashop.entity.ProductSku;
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
}
