package buy.baabaashop.service;

import buy.baabaashop.common.PaginationRequestParam;
import buy.baabaashop.common.PaginationResultData;
import buy.baabaashop.entity.OrderResult;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    PaginationResultData<OrderResult> selectOrder(PaginationRequestParam param);

}
