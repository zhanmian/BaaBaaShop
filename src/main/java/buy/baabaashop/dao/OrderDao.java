package buy.baabaashop.dao;

import buy.baabaashop.common.PaginationRequestParam;
import buy.baabaashop.entity.OrderResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDao {

    //查询订单列表
    List<OrderResult> selectOrder(PaginationRequestParam param);

    //查询订单总数量
    Integer selectOrderTotalRecord();
}
