package nick.demo.placeorder.discount;

import lombok.val;
import nick.demo.placeorder.dto.DiscountInfo;
import nick.demo.placeorder.dto.OrderDiscount;

/**
 *  'A' 開頭折扣碼，抵 100 元
 *  'B' 開頭折扣碼，抵 50 元
 *  'C' 開頭折扣碼，抵 20 元
 */
public class CouponDiscount implements IDiscountPrice{
    @Override
    public DiscountInfo calcTotalAmountByDiscount(OrderDiscount discount, Integer totalAmount) {
        val firstCode = discount.getCoupon().substring(0, 1);

        switch (firstCode){
            case "A":
                totalAmount -= 100;
                break;
            case "B":
                totalAmount -= 50;
                break;
            case "C":
                totalAmount -= 20;
                break;
        }

        return DiscountInfo.builder()
                .isNoFreight(false)
                .totalAmount(totalAmount)
                .build();
    }
}
