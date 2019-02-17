package buy.baabaashop.entity;

public class OrderResult {
    //订单ID
    private Integer id;
    //用户名
    private String username;
    //订单编号
    private String orderCode;
    //总价
    private float totalAmount;
    //订单状态：0待付款，1待发货，2已发货，3已完成，4已关闭
    private Integer status;
    //确认收货状态：0未确认，1已确认
    private Integer confirmStatus;
    //支付方式：0未支付，1支付宝，2微信支付
    private Integer payType;
    //订单创建时间
    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
