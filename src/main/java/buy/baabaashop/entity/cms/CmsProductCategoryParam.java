package buy.baabaashop.entity.cms;

import buy.baabaashop.common.PaginationRequestParam;

/**
 * Created by zhanmian on 2020/4/14
 */
public class CmsProductCategoryParam extends PaginationRequestParam {
    private Integer categoryId;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
