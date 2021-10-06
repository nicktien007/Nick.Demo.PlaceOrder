package nick.demo.placeorder.discount;

import nick.demo.placeorder.dto.DiscountInfo;
import nick.demo.placeorder.dto.OrderDiscount;

public class PercentDiscount implements IDiscountPrice{
    @Override
    public DiscountInfo calcTotalAmountByDiscount(OrderDiscount discount, Integer totalAmount) {
        return DiscountInfo.builder()
                .totalAmount((int) (totalAmount * 0.9)) //打9折
                .isNoFreight(false)
                .build();
    }
}
