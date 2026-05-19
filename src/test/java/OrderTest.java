import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {

    @Test
    void orderStatus_shouldReturnProcessing_whenOrderStatusSetToProcessing() {
        //Given
        Product product1 = new Product("Pr1", "Ipod 7th gen");
        Product product2 = new Product("Pr2", "Framework 13 Pro");

        OrderStatus orderStatus = OrderStatus.PROCESSING;
        Order testOrder1 = new Order("1", orderStatus, List.of(product1,product2));

        //When
        OrderStatus expected = testOrder1.orderStatus();

        //Then
        assertEquals(expected, testOrder1.orderStatus());
    }
}