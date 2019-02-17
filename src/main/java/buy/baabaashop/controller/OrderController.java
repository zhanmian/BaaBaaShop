package buy.baabaashop.controller;

import buy.baabaashop.common.PaginationRequestParam;
import buy.baabaashop.common.PaginationResultData;
import buy.baabaashop.entity.OrderResult;
import buy.baabaashop.service.OrderService;
import org.springframework.stereotype.Controller;
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
        return orderService.selectOrder(param);
    }

}
