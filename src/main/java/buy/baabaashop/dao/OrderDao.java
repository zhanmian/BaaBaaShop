package buy.baabaashop.dao;

import buy.baabaashop.common.PaginationRequestParam;
import buy.baabaashop.entity.OrderDetailResult;
import buy.baabaashop.entity.OrderResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDao {

    //查询订单列表
    List<OrderResult> selectOrderList(PaginationRequestParam param);

    //查询订单总数量
    Integer selectOrderTotalRecord();

    //删除订单
    void deleteOrder(@Param("id") Integer id);

    //查看订单详情
    OrderDetailResult selectOrderDetail(@Param("id") Integer id);
}
