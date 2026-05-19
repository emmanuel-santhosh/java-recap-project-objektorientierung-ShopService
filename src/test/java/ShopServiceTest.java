import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds, OrderStatus.IN_DELIVERY);

        //THEN
        Order expected = new Order("-1", OrderStatus.IN_DELIVERY, List.of(new Product("1", "Apfel")));
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    @Test
    void addOrderTest_whenInvalidProductId_expectNull() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");

        //WHEN
        Order actual = shopService.addOrder(productsIds,OrderStatus.COMPLETED);

        //THEN
        assertNull(actual);
    }

    @Test
    void getOrdersByStatus_shouldReturnOrdersMatchingStatus() {

        // Given
        ShopService shopService = new ShopService();
        List<String> shoppingList1 = List.of("1", "Pr1");
        List<String> shoppingList2 = List.of("Pr2", "Pr3");

        //When
        Order actual1 = shopService.addOrder(shoppingList1, OrderStatus.PROCESSING);

        Order actual2 = shopService.addOrder(shoppingList2, OrderStatus.IN_DELIVERY);

        //Then
        List<Order> expected1 = new ArrayList<>();
        expected1.add(new Order("-1", OrderStatus.PROCESSING, List.of(new Product("1", "Apfel"), new Product("Pr1", "Banane"))));

        List<Order> expected2 = new ArrayList<>();
        expected2.add(new Order("-2,", OrderStatus.IN_DELIVERY, List.of(new Product("Pr2", "Ipod nano"), new Product("Pr3", "Framework 13 Pro"))));

        assertEquals(expected1.getFirst().products(), shopService.getOrdersByStatus(OrderStatus.PROCESSING).getFirst().products());
        assertEquals(expected2.getFirst().products(), shopService.getOrdersByStatus(OrderStatus.IN_DELIVERY).getFirst().products());

    }
}
