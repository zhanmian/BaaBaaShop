package buy.baabaashop.entity;

import java.util.List;

public class ProductParam extends Product{

    private List<ProductSku> skuStockList;

    private List<ProductAttribute> productAttributeValueList;

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
