package nick.demo.placeorder.dto;

import lombok.Data;

import java.util.List;

@Data
public class ShoppingCart {

    private List<Item> items;
    private Integer shoppingTotalAmount;
}
