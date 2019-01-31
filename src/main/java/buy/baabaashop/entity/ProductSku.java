package buy.baabaashop.entity;

public class ProductSku {
    private Integer id;
    private Integer productId;
    private String skuCode;
    private Integer skuPrice;
    private Integer skuStock;
    //商品规格属性
    private String spec1;
    private String spec2;
    private String spec3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Integer getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(Integer skuPrice) {
        this.skuPrice = skuPrice;
    }

    public Integer getSkuStock() {
        return skuStock;
    }

    public void setSkuStock(Integer skuStock) {
        this.skuStock = skuStock;
    }

    public String getSpec1() {
        return spec1;
    }

    public void setSpec1(String spec1) {
        this.spec1 = spec1;
    }

    public String getSpec2() {
        return spec2;
    }

    public void setSpec2(String spec2) {
        this.spec2 = spec2;
    }

    public String getSpec3() {
        return spec3;
    }

    public void setSpec3(String spec3) {
        this.spec3 = spec3;
    }
}
