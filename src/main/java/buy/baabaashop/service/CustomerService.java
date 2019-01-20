package buy.baabaashop.service;


import buy.baabaashop.common.ResultData;
import buy.baabaashop.entity.CartItem;
import buy.baabaashop.entity.Customer;
import buy.baabaashop.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CustomerService {
    ResultData register(Customer customer);
    ResultData login(HttpServletRequest request, HttpServletResponse response, Customer customer);
    ResultData addCartItem(HttpServletRequest request, HttpServletResponse response, CartItem cartItem);
    String selectProductAttribute(Product product);
}
