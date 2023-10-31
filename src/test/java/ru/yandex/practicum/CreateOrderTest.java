package ru.yandex.practicum;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.scooter.api.client.OrderApiClient;
import ru.yandex.practicum.scooter.api.model.*;
import java.util.List;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.junit.Assert.*;


@RunWith(Parameterized.class)
    public class CreateOrderTest {

        private final List<String> color;
        private CreateOrderResponse createOrderResponse;
    public CreateOrderTest(List<String> color) {
            this.color = color;
        }


    @Parameterized.Parameters(name = "{index}: Заказ с цветом: {0}")
        public static Object[][] checkCreateOrderWithChoiceColor() {
            return new Object[][]{
                    {List.of("GREY")},
                    {List.of("BLACK")},
                    {List.of("GREY", "BLACK")},
                    {List.of()},
            };
        }

        @Test
        @DisplayName("Создание заказа с выбором цвета: эндпоинт /api/v1/orders")
        @Description("Проверка, что можно создать заказ на: серый цвет, черный цвет, выбор двух цветов, без выбора цвета")
        public void testCreateOrder() {
            OrderApiClient orderApiClient = new OrderApiClient();
            CreateOrderRequest createOrderRequest = new CreateOrderRequest("Naruto", "Uchiha", "Konoha, 142 apt.", "4", "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", color);
            Response OrderResponse = orderApiClient.createOrder(createOrderRequest);
            assertEquals(SC_CREATED, OrderResponse.statusCode()); //statusCode(201)
            CreateOrderResponse createOrderResponse = OrderResponse.as(CreateOrderResponse.class);
            assertNotNull(createOrderResponse.track);

        }
    @After
    public void tearDown() {
        if (createOrderResponse != null) {
        OrderApiClient orderApiClient = new OrderApiClient();
        orderApiClient.deleteTrack(createOrderResponse.track);
    }
    }

}



