package nick.demo.placeorder.dto;

import lombok.Data;

@Data
public class OrderInfo {

    private ShoppingCart cart;
    private OrderDiscount discount;
    private Integer freight;
    private Integer totalAmount;
}
