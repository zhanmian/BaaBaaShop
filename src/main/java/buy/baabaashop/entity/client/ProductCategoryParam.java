package buy.baabaashop.entity.client;

import buy.baabaashop.common.PaginationRequestParam;

/**
 * 买家在分类页面进行筛选，传给接口的参数
 * Created by zhanmian on 2020/4/12
 */
public class ProductCategoryParam extends PaginationRequestParam {

    /**
     * 分类ID
     */
    private Integer id;

    /**
     * 1-最新发布；2-价格从高到低；3-价格从低到高
     */
    private Integer displayOrder;

    /**
     * 最低价格
     */
    private Integer minPrice;

    /**
     * 最高价格
     */
    private Integer maxPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }
}
