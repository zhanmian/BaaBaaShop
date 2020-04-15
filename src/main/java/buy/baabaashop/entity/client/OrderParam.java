package buy.baabaashop.entity.client;

import buy.baabaashop.entity.client.Address;
import buy.baabaashop.entity.client.CartItem;

import java.util.List;

/**
 * 买家下单传给接口的参数
 * Created by zhanmian on 2020/4/11
 */
public class OrderParam {
    //用户ID
    private Integer userId;
    //订单ID
    private Integer orderId;
    //收货地址
    private Address address;
    //购物车商品列表
    private List<CartItem> cartItems;
    //用户留言
    private String buyerMessage;
    //支付方式
    private Integer payType;
    //金额
    private float totalAmount;
    //订单编号
    private String orderCode;
    //订单状态
    private Integer status;
    //订单确认状态
    private Integer confirmStatus;
    //支付交易流水号
    private String payCode;

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(Integer confirmStatus) {
        this.confirmStatus = confirmStatus;
    }
}
