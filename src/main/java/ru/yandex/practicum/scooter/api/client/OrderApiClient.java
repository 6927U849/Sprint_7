package ru.yandex.practicum.scooter.api.client;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import ru.yandex.practicum.scooter.api.model.CreateOrderRequest;
import ru.yandex.practicum.scooter.api.model.ListOrderRequest;

import static ru.yandex.practicum.scooter.api.config.ConfigApp.BASE_URl;
public class OrderApiClient extends BaseApiClient {


    private RestAssured getSpec;
    @Step("Создание заказа")
    public Response createOrder(CreateOrderRequest createOrderRequest) {

        {
            return getPostSpec()
                    .body(createOrderRequest)
                    .when()
                    .post(BASE_URl + "/api/v1/orders");
        }

    }
    @Step("Получение списка заказов")
    public Response listOrder(ListOrderRequest listOrderRequest) {
        Response response = getPostSpec()
                .body(listOrderRequest)
                .when()
                .get(BASE_URl + "/api/v1/orders");
        return response;
    }

    @Step("Удаление заказа")
    public Response deleteTrack(int track) {
        return getPostSpec()
                .when()
                .delete(BASE_URl + "/api/v1/orders" + track);
    }
}
