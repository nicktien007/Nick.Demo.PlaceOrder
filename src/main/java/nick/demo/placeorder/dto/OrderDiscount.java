package nick.demo.placeorder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDiscount {

    private DiscountType discountType;
    private Integer bonus;
    private String coupon;
    private String holiday;

    /**
     * 運費折扣金額
     */
    private Integer freightDiscount;
}