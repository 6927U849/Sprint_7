package ru.yandex.practicum;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import ru.yandex.practicum.scooter.api.client.OrderApiClient;
import ru.yandex.practicum.scooter.api.model.ListOrderRequest;
import ru.yandex.practicum.scooter.api.model.ListOrderResponse;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.*;

public class ListOrderTest  {

    @Test
    @DisplayName("Получение списка заказов: эндпоинт /api/v1/orders")
    @Description("Проверка ожидаемого результата: statusCode и body")
    public void testListOrderResponseNotEmpty() {
        OrderApiClient orderApiClient = new OrderApiClient();
        ListOrderRequest listOrderRequest = new ListOrderRequest();
        Response listResponse = orderApiClient.listOrder(listOrderRequest);
        ListOrderResponse listOrderResponse = listResponse.as(ListOrderResponse.class);
        assertEquals(SC_OK, listResponse.statusCode());  //statusCode(200)
        assertNotNull(listOrderResponse);

    }
    }


//JsonPath jsonPathEvaluator = listResponse.jsonPath();

//System.out.println("orders Response " + jsonPathEvaluator.get("orders[0].id"));
//System.out.println("pageInfo Response " + jsonPathEvaluator.get("pageInfo.page"));
//System.out.println("availableStations Response " + jsonPathEvaluator.get("availableStations[0].name"));