package nick.demo.placeorder.discount;

import nick.demo.placeorder.dto.DiscountInfo;
import nick.demo.placeorder.dto.OrderDiscount;

/**
 * 根據訂購總額，依序扣除 Bonus 點數費用
 *  1. 1000元(含)以下，折抵最大點數：50
 *  2. 5000元(含)以下，折抵最大點數：300
 *  3. 5000元以上，折抵最大點數：1000
 */
public class BonusDiscount implements IDiscountPrice{
    @Override
    public DiscountInfo calcTotalAmountByDiscount(OrderDiscount discount, Integer totalAmount) {
        Integer bonus = discount.getBonus();

        if (totalAmount <= 1000) {
            totalAmount -= Math.min(bonus, 50);
        }
        else if (totalAmount <= 5000) {
            totalAmount -= Math.min(bonus, 300);
        }
        else{
            totalAmount -= Math.min(bonus, 1000);
        }

        return DiscountInfo.builder()
                .isNoFreight(false)     //沒有免運優惠
                .totalAmount(totalAmount)
                .build();
    }
}
