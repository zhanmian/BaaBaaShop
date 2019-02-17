package buy.baabaashop.service;

import buy.baabaashop.common.PaginationRequestParam;
import buy.baabaashop.common.PaginationResultData;
import buy.baabaashop.common.ResultData;
import buy.baabaashop.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ProductService {

    PaginationResultData<Product> getProductList(PaginationRequestParam param);

    PaginationResultData<ProductCategory> selectProductCategoryList(PaginationRequestParam param);

    ResultData addProductCategory(ProductCategory productCategory);

    ResultData updateProductCategory(ProductCategory productCategory);

    ProductCategory getProductCategoryById(ProductCategory productCategory);

    PaginationResultData<ProductAttribute> selectProductAttributeCategory(PaginationRequestParam param);

    ResultData addProductAttributeCategory(ProductAttribute productAttribute);

    ResultData updateProductAttributeCategory(ProductAttribute productAttribute);

    PaginationResultData<ProductAttribute> selectProductAttribute(PaginationRequestParam param);

    ResultData addProductAttribute(ProductAttribute productAttribute);

    ProductAttribute getProductAttributeById(ProductAttribute productAttribute);

    ResultData updateProductAttribute(ProductAttribute productAttribute);

    ResultData addProduct(ProductParam productParam);

    ResultData updateProduct(ProductParam productParam);

    ProductParam getProductDetail(Integer id);

    Object getSkuDetails(Integer productId);

    ResultData deleteProductCategory(Integer categoryId);

    ResultData deleteProduct(Integer productId);

    ResultData deleteProductAttributeCategory(Integer categoryId);

    ResultData deleteProductAttribute(ProductAttribute productAttribute);

    List<ProductCategoryWithChildrenItem> getProductCategoryWithChildren();
}
