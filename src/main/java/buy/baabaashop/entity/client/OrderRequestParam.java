package buy.baabaashop.entity.client;

import buy.baabaashop.common.PaginationRequestParam;

/**
 * 买家在个人中心获取订单列表传给接口的参数
 * Created by zhanmian on 2020/4/13
 */
public class OrderRequestParam extends PaginationRequestParam {
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
