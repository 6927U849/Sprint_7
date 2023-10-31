package ru.yandex.practicum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.scooter.api.client.CourierApiClient;
import ru.yandex.practicum.scooter.api.heiper.CourierGenerator;
import ru.yandex.practicum.scooter.api.model.CreateCourierRequest;
import ru.yandex.practicum.scooter.api.model.DeleteCourierResponse;
import ru.yandex.practicum.scooter.api.model.LoginCourierRequest;
import ru.yandex.practicum.scooter.api.model.LoginCourierResponse;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.*;

public class DeleteLoginTest {

    CreateCourierRequest createCourierRequest;
    LoginCourierRequest loginCourierRequest;
    CourierApiClient courierApiClient;
    Response createResponse;
    Response loginResponse;


    @Before
    public void setup() {
        courierApiClient = new CourierApiClient();
        createCourierRequest = CourierGenerator.getRandomCourier();
        createResponse = courierApiClient.createCourier(createCourierRequest);
    }
    @Test
    @DisplayName("Удаление логин курьера в системе: эндпоинт api/v1/courier/login")
    @Description("Проверка ожидаемого результата: statusCode и body")

    public void deleteCourierById() {
        loginCourierRequest = new LoginCourierRequest(createCourierRequest.login, createCourierRequest.password);
        loginResponse = courierApiClient.loginCourier(loginCourierRequest);
        LoginCourierResponse loginCourierResponse = loginResponse.as(LoginCourierResponse.class);
        int courierId = loginCourierResponse.getId();
        Response deleteResponse = courierApiClient.deleteCourier(courierId);
        assertEquals(SC_OK, deleteResponse.getStatusCode()); //statusCode(200)
        DeleteCourierResponse deleteCourierResponse = deleteResponse.as(DeleteCourierResponse.class);
        assertTrue(deleteCourierResponse.getOk());
    }

    @Test
    @DisplayName("Удаление курьера с несуществующим идентификатором: эндпоинт api/v1/courier/login")
    @Description("Проверка ожидаемого результата: statusCode 404 и body")
    public void deleteCourierWithNonExistingId() {
        int nonExistingCourierId = 9999; // Несуществующий идентификатор курьера (позже сделать рандом)
        Response deleteResponse = courierApiClient.deleteCourier(nonExistingCourierId);
        assertEquals(SC_NOT_FOUND, deleteResponse.getStatusCode()); //  statusCode (404)
        String errorMessage = deleteResponse.getBody().asString();
        assertTrue(errorMessage.contains("Курьера с таким id нет"));

    }

    @Test
    @DisplayName("Удаление курьера без указания ID: эндпоинт api/v1/courier/login")
    @Description("Проверка ожидаемого результата: statusCode 404")
    public void deleteCourierWithoutId() {
        Response deleteResponse = courierApiClient.deleteCourierWithoutId();
        assertNotNull(deleteResponse);
       assertEquals(SC_NOT_FOUND, deleteResponse.getStatusCode()); //statusCode (404)
        String errorMessage = deleteResponse.getBody().asString();
        assertTrue(errorMessage.contains("Not Found."));
    }
}
