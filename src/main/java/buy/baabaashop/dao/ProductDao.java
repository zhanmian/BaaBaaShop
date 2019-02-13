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

    //删除商品分类
    void deleteProductCategory(@Param("id") Integer categoryId);
    //删除商品分类的同时删除子分类
    void deleteProductSubcategory(@Param("id") Integer categoryId);
    //删除商品
    void deleteProduct(@Param("id") Integer productId);
    //删除商品的同时删除添加商品时手动添加的商品属性
    void deleteProductAttributeValue(@Param("id") Integer productId);
    //删除商品也要删除关联的SKU
    void deleteSku(@Param("id") Integer productId);
    //删除商品属性分类
    void deleteProductAttributeCategory(@Param("id") Integer categoryId);
    //删除商品属性分类的同时删除所属的商品属性
    void deleteProductAttributeByCategoryId(@Param("id") Integer categoryId);
    //单独删除商品属性
    void deleteProductAttribute(@Param("id") Integer attributeId);
}
