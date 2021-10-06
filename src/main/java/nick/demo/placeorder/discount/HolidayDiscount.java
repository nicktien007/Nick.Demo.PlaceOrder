package nick.demo.placeorder.discount;

import nick.demo.placeorder.dto.DiscountInfo;
import nick.demo.placeorder.dto.OrderDiscount;

public class HolidayDiscount implements IDiscountPrice{
    @Override
    public DiscountInfo calcTotalAmountByDiscount(OrderDiscount discount, Integer totalAmount) {

        DiscountInfo discountInfo = new DiscountInfo();
        switch (discount.getHoliday()){
            case "母親節":
                int discountAmount = (totalAmount / 1000) * 100;//滿千送百
                totalAmount -= discountAmount;
                discountInfo.setIsNoFreight(false);
                discountInfo.setTotalAmount(totalAmount);
                break;
            case "光棍節":
                discountInfo.setIsNoFreight(true);              //免運
                discountInfo.setTotalAmount(totalAmount);
                break;
        }

        return discountInfo;
    }
}
