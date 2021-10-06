package nick.demo.placeorder.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
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

enum DiscountType {
    None,
    Bonus,
    Coupon,
    Holiday,
    Percent
}