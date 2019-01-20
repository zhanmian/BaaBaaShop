package buy.baabaashop.service;

import buy.baabaashop.common.PaginationRequestParam;
import buy.baabaashop.common.PaginationResultData;
import buy.baabaashop.common.ResultData;
import buy.baabaashop.entity.Product;
import buy.baabaashop.entity.ProductAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ProductService {

    PaginationResultData<Product> getProductList(PaginationRequestParam param);
    PaginationResultData<Product> selectProductCategoryList(PaginationRequestParam param);
    List<Product> selectAllParentProductCategory();
    ResultData addProductCategory(Product product);
    PaginationResultData<Product> selectProductAttributeCategory(PaginationRequestParam param);
    ResultData addProductAttributeCategory(ProductAttribute productAttribute);
    PaginationResultData<ProductAttribute> selectProductAttribute(PaginationRequestParam param);
    ResultData addProductAttribute(ProductAttribute productAttribute);
    ResultData updateProductAttribute(ProductAttribute productAttribute);
    ResultData addProduct(HttpServletRequest request);
    Object getSkuDetails(Integer productId);
}
