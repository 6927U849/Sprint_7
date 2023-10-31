package ru.yandex.practicum.scooter.api.client;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.practicum.scooter.api.model.CreateCourierRequest;
import ru.yandex.practicum.scooter.api.model.LoginCourierRequest;

import static ru.yandex.practicum.scooter.api.config.ConfigApp.BASE_URl;

public class CourierApiClient extends BaseApiClient {


    @Step("Создание курьера")
    public Response createCourier(CreateCourierRequest createCourierRequest) {
        return getPostSpec()
                .body(createCourierRequest)
                .when()
                .post(BASE_URl + "/api/v1/courier");
    }
    @Step("Создание логин курьера в системе")
    public Response loginCourier(LoginCourierRequest loginCourierRequest) {
        return getPostSpec()
                .body(loginCourierRequest)
                .when()
                .post(BASE_URl + "/api/v1/courier/login");
    }
   @Step("Удаление id курьера")
    public Response deleteCourier(int id) {
        return getPostSpec()
                .when()
                .delete(BASE_URl + "/api/v1/courier/" + id);
    }

    @Step("Удаление курьера без указания ID")
    public Response deleteCourierWithoutId() {
        return getPostSpec()
                .when()
                .delete(BASE_URl + "/api/v1/courier");
    }

}
