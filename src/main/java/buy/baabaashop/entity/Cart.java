package buy.baabaashop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();
    private float totalPrice;

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public void addItem(CartItem item){
        //判断是否包含同款
        if(items.contains(item)){
            for(CartItem cartItem : items){
                //如果是同款就数量相加
                if(cartItem.equals(item)){
                    cartItem.setQuantity(item.getQuantity() + cartItem.getQuantity());
                }
            }
        }else{
            items.add(item);
        }
    }
    @JsonIgnore
    public float getTotalPrice(){
        float result = 0f;
        for(CartItem item : items){
            result += item.getQuantity()*item.getSkuPrice();
        }
        return result;
    }
}
