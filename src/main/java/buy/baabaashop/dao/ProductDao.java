package buy.baabaashop.dao;

import buy.baabaashop.common.PaginationRequestParam;
import buy.baabaashop.entity.Product;
import buy.baabaashop.entity.ProductAttribute;
import buy.baabaashop.entity.ProductSku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductDao {

    List<Product> selectProductList(PaginationRequestParam param);
    Product selectProductCode(@Param("productId") Integer productId);
    List<ProductSku> selectSkuDetails(@Param("productId") Integer productId);
    List<Product> selectProductCategoryList(PaginationRequestParam param);
    List<Product> selectAllParentProductCategory();
    List<Product> selectProductAttributeCategory(PaginationRequestParam param);
    List<ProductAttribute> selectAllProductAttributeCategory();
    List<ProductAttribute> selectProductAttribute(PaginationRequestParam param);
    List<ProductAttribute> selectProductAttributeByCategoryId(ProductAttribute productAttribute);
    Integer selectProductTotalRecord();
    Integer selectProductCategoryTotalRecord();
    Integer selectProductAttributeCategoryTotalRecord();
    Integer selectProductAttributeCategoryAttributeCount(@Param("categoryId") Integer categoryId);
    Integer selectProductAttributeCategoryParamCount(@Param("categoryId") Integer categoryId);
    Integer selectProductAttributeTotalRecord(PaginationRequestParam param);
    ProductAttribute selectProductAttributeById(ProductAttribute productAttribute);
    void updateProductAttributeCategoryAttributeCount(ProductAttribute productAttribute);
    void updateProductAttributeCategoryParamCount(ProductAttribute productAttribute);
    void updateProductAttribute(ProductAttribute productAttribute);
    void addProductCategory(Product product);
    void addProductAttributeCategory(ProductAttribute productAttribute);
    void addProductAttribute(ProductAttribute productAttribute);
    void addProduct(Product product);
    void addProductSku(ProductSku productSku);
    void addAttributeValue(ProductAttribute productAttribute);
}
