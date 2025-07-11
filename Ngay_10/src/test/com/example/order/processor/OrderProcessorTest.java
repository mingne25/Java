package test.com.example.order.processor;

import com.example.order.model.Order;
import com.example.order.model.OrderItem;
import com.example.order.processor.OrderProcessor;
import com.example.order.service.InventoryService;
import com.example.order.service.ShippingService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Tag("unit")
class OrderProcessorTest {

    private InventoryService inventoryService;
    private ShippingService shippingService;
    private OrderProcessor processor;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before All Tests");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After All Tests");
    }

    @BeforeEach
    void setUp() {
        inventoryService = mock(InventoryService.class);
        shippingService = mock(ShippingService.class);
        processor = new OrderProcessor(inventoryService, shippingService);
    }

    private com.example.service.InventoryService mock(Class<com.example.service.InventoryService> inventoryServiceClass) {
    }

    @AfterEach
    void tearDown() {
        System.out.println("After Each Test");
    }

    @Nested
    @DisplayName("Order Processing Tests")
    class ProcessingTests {

        @Test
        @DisplayName("Should process order successfully when inventory is available")
        void testProcessSuccess() {
            Order order = new Order("ORD1", "Bob", List.of(new OrderItem("P1", 1, 10)), 10, LocalDateTime.now());
            when(inventoryService.checkInventory(order)).thenReturn(true);

            boolean result = processor.process(order);

            assertTrue(result);
            verify(inventoryService).checkInventory(order);
            verify(shippingService).shipOrder(order);
        }

        @Test
        @DisplayName("Should fail when inventory check fails")
        void testInventoryFails() {
            Order order = new Order("ORD2", "Tom", List.of(new OrderItem("P2", 2, 15)), 30, LocalDateTime.now());
            when(inventoryService.checkInventory(order)).thenReturn(false);

            boolean result = processor.process(order);

            assertFalse(result);
            verify(inventoryService).checkInventory(order);
            verify(shippingService, never()).shipOrder(any());
        }

        @Test
        @DisplayName("Should handle null order gracefully")
        void testNullOrder() {
            boolean result = processor.process(null);
            assertFalse(result);
            verify(inventoryService, never()).checkInventory(any());
            verify(shippingService, never()).shipOrder(any());
        }

        @ParameterizedTest
        @CsvSource({
                "P1, 1, 10.0, 10.0",
                "P2, 2, 15.0, 30.0"
        })
        @DisplayName("Should process order with various item quantities and prices")
        void testProcessWithVariousItems(String productId, int quantity, double price, double total) {
            Order order = new Order("ORD" + productId, "TestUser", List.of(new OrderItem(productId, quantity, price)), total, LocalDateTime.now());
            when(inventoryService.checkInventory(order)).thenReturn(true);

            boolean result = processor.process(order);

            assertTrue(result);
            verify(inventoryService).checkInventory(order);
            verify(shippingService).shipOrder(order);
        }
    }
}