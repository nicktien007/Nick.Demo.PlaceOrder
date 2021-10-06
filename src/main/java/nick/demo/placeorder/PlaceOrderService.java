package nick.demo.placeorder;

import com.google.common.base.Strings;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nick.demo.placeorder.discount.*;
import nick.demo.placeorder.dto.DiscountInfo;
import nick.demo.placeorder.dto.Item;
import nick.demo.placeorder.dto.OrderInfo;
import nick.demo.placeorder.dto.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;



@Slf4j
@Service
@AllArgsConstructor
public class PlaceOrderService {
    public String PlaceOrder(OrderInfo orderInfo){

        //計算購物車總額
        ShoppingCart cart = orderInfo.getCart();
        cart = this.calcShoppingCartTotalAmount(cart);

        //計算折扣後的訂單總金額
        orderInfo = this.calcOrderAmountbyDiscount(orderInfo, cart.getShoppingTotalAmount());

        //計算加上運費後的總金額
        orderInfo.setTotalAmount(orderInfo.getTotalAmount() + orderInfo.getFreight());

        return this.formatOrderResult(orderInfo);
    }

    private ShoppingCart calcShoppingCartTotalAmount(ShoppingCart cart) {
        int total = 0;

        for (Item item : cart.getItems()) {
            int subTotal;
            subTotal = item.getQuanity() * item.getUnitPrice();
            item.setSubTotal(subTotal);
            total += subTotal;
        }

        cart.setShoppingTotalAmount(total);
        return cart;
    }

    private OrderInfo calcOrderAmountbyDiscount(OrderInfo orderInfo, int cartTotalAmount)
    {
        DiscountInfo discountInfo;
        orderInfo.setTotalAmount(cartTotalAmount);

        // 依據折扣類型，計算折扣費用
        switch (orderInfo.getDiscount().getDiscountType())
        {
            case None:     // No Discount
                break;

            case Bonus:
                IDiscountPrice bonusDiscount = new BonusDiscount();
                discountInfo = bonusDiscount.calcTotalAmountByDiscount(orderInfo.getDiscount(), orderInfo.getTotalAmount());
                orderInfo = this.setTotalAmountbyDiscount(orderInfo, discountInfo);

                // Todo: 未處理未扣除與消費點數還回給原消費者
                break;

            case Coupon:
                IDiscountPrice couponDiscount = new CouponDiscount();
                discountInfo = couponDiscount.calcTotalAmountByDiscount(orderInfo.getDiscount(), orderInfo.getTotalAmount());
                orderInfo = this.setTotalAmountbyDiscount(orderInfo, discountInfo);
                break;
            case Holiday:
                IDiscountPrice holidayDiscount = new HolidayDiscount();
                discountInfo = holidayDiscount.calcTotalAmountByDiscount(orderInfo.getDiscount(), orderInfo.getTotalAmount());
                orderInfo = this.setTotalAmountbyDiscount(orderInfo, discountInfo);
                break;
            case Percent:
                IDiscountPrice percentDiscount = new PercentDiscount();
                discountInfo = percentDiscount.calcTotalAmountByDiscount(orderInfo.getDiscount(), orderInfo.getTotalAmount());
                orderInfo=this.setTotalAmountbyDiscount(orderInfo, discountInfo);
                break;
        }

        return orderInfo;
    }

    private OrderInfo setTotalAmountbyDiscount(OrderInfo orderInfo, DiscountInfo discountInfo)
    {
        orderInfo.setTotalAmount(discountInfo.getTotalAmount());
        if (discountInfo.getIsNoFreight())
            orderInfo.setFreight(0);      // 免運費

        return orderInfo;
    }

    private String formatOrderResult(OrderInfo orderInfo)
    {
        String result;
        List<Item> items = orderInfo.getCart().getItems();

        // 表頭 (header)
        result = "------------------------------ 訂購商品 -------------------------------\n";
        result += "|　商品名稱　　　　　　|　購買數量　|　　　單價　　　|　分項金額　|\n";

        // 表身 (body)
        for (Item item : orderInfo.getCart().getItems()) {
            result += String.format("|　%s　　%s　　　%06d　 　%06d　　|\n",
                    Strings.padEnd(item.getName(), 12, '　'),
                    Strings.padEnd(item.getQuanity().toString(), 4, '　'),
                    item.getUnitPrice(),
                    item.getSubTotal()
            );
        }


        // 註腳 (footer)
        result += "-----------------------------------------------------------------------\n";

        switch (orderInfo.getDiscount().getDiscountType())
        {
            case None:
                result += "折扣類型：無" + "\n";
                break;
            case Bonus:
                result += "折扣類型：Bonus ; " + "折扣金額：" + orderInfo.getDiscount().getBonus().toString() + "\n";
                break;
            case Coupon:
                result += "折扣類型：Coupon ; " + "折扣代碼：" + orderInfo.getDiscount().getCoupon() + "\n";
                break;
            case Holiday:
                result += "折扣類型：節慶折扣 ; " + "節日：" + orderInfo.getDiscount().getHoliday() + "\n";
                break;
        }

        result += "訂購總額：" + orderInfo.getTotalAmount() + "\n";
        return result;
    }
}