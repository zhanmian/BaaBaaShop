package buy.baabaashop.service;

import buy.baabaashop.common.PaginationRequestParam;
import buy.baabaashop.common.PaginationResultData;
import buy.baabaashop.dao.OrderDao;
import buy.baabaashop.entity.OrderResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Override
    public PaginationResultData<OrderResult> selectOrder(PaginationRequestParam param){
        PaginationResultData<OrderResult> resultData = new PaginationResultData<>();
        Integer totalRecord = orderDao.selectOrderTotalRecord();
        List<OrderResult> orderList = orderDao.selectOrder(param);
        resultData.setTotalRecord(totalRecord);
        resultData.setList(orderList);
        return resultData;
    }
}
