package buy.baabaashop.service;

import buy.baabaashop.common.PaginationRequestParam;
import buy.baabaashop.common.PaginationResultData;
import buy.baabaashop.common.ResultData;
import buy.baabaashop.entity.Product;
import buy.baabaashop.entity.ProductAttribute;
import buy.baabaashop.entity.ProductCategory;
import buy.baabaashop.entity.ProductCategoryWithChildrenItem;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ProductService {

    PaginationResultData<Product> getProductList(PaginationRequestParam param);

    PaginationResultData<ProductCategory> selectProductCategoryList(PaginationRequestParam param);

    ResultData addProductCategory(Product product);

    PaginationResultData<ProductAttribute> selectProductAttributeCategory(PaginationRequestParam param);

    ResultData addProductAttributeCategory(ProductAttribute productAttribute);

    PaginationResultData<ProductAttribute> selectProductAttribute(PaginationRequestParam param);

    ResultData addProductAttribute(ProductAttribute productAttribute);

    ResultData updateProductAttribute(ProductAttribute productAttribute);

    ResultData addProduct(HttpServletRequest request);

    Object getSkuDetails(Integer productId);

    ResultData deleteProductCategory(Integer categoryId);

    ResultData deleteProduct(Integer productId);

    ResultData deleteProductAttributeCategory(Integer categoryId);

    ResultData deleteProductAttribute(Integer id);

    List<ProductCategoryWithChildrenItem> getProductCategoryWithChildren();
}
