package buy.baabaashop.controller;

import buy.baabaashop.common.PaginationRequestParam;
import buy.baabaashop.common.PaginationResultData;
import buy.baabaashop.common.ResultData;
import buy.baabaashop.entity.cms.OrderDetailResult;
import buy.baabaashop.entity.OrderResult;
import buy.baabaashop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class OrderController {

    @Resource
    private OrderService orderService;

    @RequestMapping(value = "get_order_list")
    @ResponseBody
    public PaginationResultData<OrderResult> getOrderList(
            @RequestBody PaginationRequestParam param){
        return orderService.selectOrderList(param);
    }

    @RequestMapping(value = "delete_order/{id}")
    @ResponseBody
    public ResultData deleteOrder(@PathVariable Integer id){
        return orderService.deleteOrder(id);
    }

    @RequestMapping(value = "get_order_detail/{id}")
    @ResponseBody
    public OrderDetailResult getOrderDetail(@PathVariable Integer id){
        return orderService.getOrderDetail(id);
    }

}
