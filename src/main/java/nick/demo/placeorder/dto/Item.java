package nick.demo.placeorder.dto;

import lombok.Data;

@Data
public class Item {

    private String name;
    private Integer quanity;
    private Integer unitPrice;
    private Integer subTotal;
}
