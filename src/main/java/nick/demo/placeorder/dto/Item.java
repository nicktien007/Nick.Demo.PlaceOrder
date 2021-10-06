package nick.demo.placeorder.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {

    private String name;
    private Integer quanity;
    private Integer unitPrice;
    private Integer subTotal;
}
