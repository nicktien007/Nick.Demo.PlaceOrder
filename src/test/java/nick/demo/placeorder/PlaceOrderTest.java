package nick.demo.placeorder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nick.demo.placeorder.dto.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class PlaceOrderTest {
    private final PlaceOrderService placeOrderService = new PlaceOrderService();

    @Test
    void testTotalAmountNoAnyDiscount() {
        OrderInfo orderInfo = new OrderInfo();
        ShoppingCart cart = new ShoppingCart();

        List<Item> items = new ArrayList<>();
        items.add(Item.builder().name("[家電]電動按摩椅").quanity(1).unitPrice(350).build());
        items.add(Item.builder().name("[電子]行動電源").quanity(1).unitPrice(400).build());
        items.add(Item.builder().name("[書本]單元測試的藝術").quanity(2).unitPrice(70).build());

        cart.setItems(items);
        orderInfo.setCart(cart);

        OrderDiscount discount = new OrderDiscount();
        discount.setDiscountType(DiscountType.None);
        orderInfo.setDiscount(discount);

        orderInfo.setFreight(100);


        String result = placeOrderService.PlaceOrder(orderInfo);
        System.out.print(result);
    }
}