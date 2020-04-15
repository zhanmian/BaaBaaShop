package buy.baabaashop.entity.cms;

import buy.baabaashop.entity.ProductCategory;

import java.util.List;

//继承了ProductCategory，查询数据库后父类下就带有一个children的List
public class ProductCategoryWithChildrenItem extends ProductCategory {

    //MyBatis里用collection集合所有子分类
    private List<ProductCategory> children;

    public List<ProductCategory> getChildren() {
        return children;
    }

    public void setChildren(List<ProductCategory> children) {
        this.children = children;
    }
}
