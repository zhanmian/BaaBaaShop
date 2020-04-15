package buy.baabaashop.entity.cms;

import buy.baabaashop.entity.client.CartItem;

import java.util.List;

public class Order {
    //订单ID
    private Integer id;
    //用户ID
    private Integer customerId;
    //订单编号
    private String orderCode;
    //购物车商品列表
    private List<CartItem> cartItemList;
    //运费
    private float freight;
    //总价
    private float totalAmount;
    //订单状态：0待付款，1待发货，2已发货，3已完成，4已关闭
    private Integer status;
    //确认收货状态：0未确认，1已确认
    private Integer confirmStatus;
    //支付方式：0未支付，1支付宝，2微信支付
    private Integer payType;
    //支付时间
    private String paymentTime;
    //订单创建时间
    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public float getFreight() {
        return freight;
    }

    public void setFreight(float freight) {
        this.freight = freight;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
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

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
