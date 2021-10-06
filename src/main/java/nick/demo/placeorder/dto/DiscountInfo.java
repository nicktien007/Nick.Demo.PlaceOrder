package nick.demo.placeorder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscountInfo {
    private Integer totalAmount;
    private Boolean isNoFreight;
}
