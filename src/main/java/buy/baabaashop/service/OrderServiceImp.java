package buy.baabaashop.service;

import buy.baabaashop.common.CommonException;
import buy.baabaashop.common.PaginationRequestParam;
import buy.baabaashop.common.PaginationResultData;
import buy.baabaashop.common.ResultData;
import buy.baabaashop.dao.OrderDao;
import buy.baabaashop.entity.OrderDetailResult;
import buy.baabaashop.entity.OrderResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Override
    public PaginationResultData<OrderResult> selectOrderList(PaginationRequestParam param){
        PaginationResultData<OrderResult> resultData = new PaginationResultData<>();
        Integer totalRecord = orderDao.selectOrderTotalRecord();
        List<OrderResult> orderList = orderDao.selectOrderList(param);
        resultData.setTotalRecord(totalRecord);
        resultData.setList(orderList);
        return resultData;
    }

    @Override
    public ResultData deleteOrder(Integer id){
        ResultData resultData = new ResultData();
        try{
            orderDao.deleteOrder(id);
            resultData.setMessage("成功删除订单");
        }catch(CommonException c){
            c.printStackTrace();
            throw new CommonException(c.getCode(), c.getMessage());
        }catch(Exception e){
            e.printStackTrace();
            throw new CommonException();
        }
        return resultData;
    }

    @Override
    public OrderDetailResult getOrderDetail(Integer id) {
        OrderDetailResult result = orderDao.selectOrderDetail(id);
        return result;
    }
}
