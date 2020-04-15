package buy.baabaashop.entity.client;

/**
 * 买家搜索商品，传给接口的参数
 * Created by zhanmian on 2020/4/13
 */
public class ProductSearchParam extends ProductCategoryParam {
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
