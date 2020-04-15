package buy.baabaashop.entity.cms;

import buy.baabaashop.entity.Product;
import buy.baabaashop.entity.ProductAttribute;
import buy.baabaashop.entity.ProductSku;

import java.util.List;

public class ProductParam extends Product {

    private Integer productCategoryParentId;

    private List<ProductSku> skuStockList;

    private List<ProductAttribute> productAttributeValueList;

    public Integer getProductCategoryParentId() {
        return productCategoryParentId;
    }

    public void setProductCategoryParentId(Integer productCategoryParentId) {
        this.productCategoryParentId = productCategoryParentId;
    }

    public List<ProductSku> getSkuStockList() {
        return skuStockList;
    }

    public void setSkuStockList(List<ProductSku> skuStockList) {
        this.skuStockList = skuStockList;
    }

    public List<ProductAttribute> getProductAttributeValueList() {
        return productAttributeValueList;
    }

    public void setProductAttributeValueList(List<ProductAttribute> productAttributeValueList) {
        this.productAttributeValueList = productAttributeValueList;
    }
}
