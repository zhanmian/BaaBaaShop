package buy.baabaashop.entity.cms;

import buy.baabaashop.common.PaginationRequestParam;

/**
 * 管理员在新建或更新商品时，获取规格属性，传给接口的参数
 * Created by zhanmian on 2020/4/14
 */
public class ProductAttributeRequestParam extends PaginationRequestParam {
    private Integer type;
    private Integer categoryId;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
