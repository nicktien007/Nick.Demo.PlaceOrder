package nick.demo.placeorder.discount;

import nick.demo.placeorder.dto.DiscountInfo;
import nick.demo.placeorder.dto.OrderDiscount;

public interface IDiscountPrice {
    DiscountInfo calcTotalAmountByDiscount(OrderDiscount discount, Integer totalAmount);
}
