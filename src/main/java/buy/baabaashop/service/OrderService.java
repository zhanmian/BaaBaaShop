package buy.baabaashop.service;

import buy.baabaashop.common.PaginationRequestParam;
import buy.baabaashop.common.PaginationResultData;
import buy.baabaashop.common.ResultData;
import buy.baabaashop.entity.OrderDetailResult;
import buy.baabaashop.entity.OrderResult;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    PaginationResultData<OrderResult> selectOrderList(PaginationRequestParam param);

    ResultData deleteOrder(Integer id);

    OrderDetailResult getOrderDetail(Integer id);

}
