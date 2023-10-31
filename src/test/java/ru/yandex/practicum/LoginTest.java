package ru.yandex.practicum;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.scooter.api.client.CourierApiClient;
import ru.yandex.practicum.scooter.api.heiper.CourierGenerator;
import ru.yandex.practicum.scooter.api.model.*;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.*;

public class LoginTest {

    CreateCourierRequest createCourierRequest;
    LoginCourierRequest loginCourierRequest;
    CourierApiClient courierApiClient;
    Response createResponse;



    @Before
    public void setup() {
        courierApiClient = new CourierApiClient();
        createCourierRequest = CourierGenerator.getRandomCourier();
        createResponse = courierApiClient.createCourier(createCourierRequest);
    }


    @Test
    @DisplayName("Логин курьера в системе: эндпоинт api/v1/courier/login")
    @Description("Проверка ожидаемого результата: statusCode и body")
    public void createLogin() {
        loginCourierRequest = new LoginCourierRequest(createCourierRequest.login, createCourierRequest.password);
        Response loginResponse = courierApiClient.loginCourier(loginCourierRequest);
        assertEquals(SC_OK, loginResponse.statusCode());  //statusCode(200)
        LoginCourierResponse loginCourierResponse = loginResponse.as(LoginCourierResponse.class);
        assertNotNull(loginCourierResponse.getId());
    }


    @Test
    @DisplayName("Создание логин курьера в системе, без логина: эндпоинт логина api/v1/courier/login")
    @Description("Проверка ожидаемого результата: statusCode и body")
    public void loginWithoutLogin() {
        loginCourierRequest = new LoginCourierRequest(null, createCourierRequest.password);
        Response loginResponse = courierApiClient.loginCourier(loginCourierRequest);
        assertEquals(SC_BAD_REQUEST, loginResponse.statusCode());  //statusCode(400)
        String errorMessage = loginResponse.getBody().asString();
        assertTrue(errorMessage.contains("Недостаточно данных для входа"));
    }


    @Test
    @DisplayName("Создание логин курьера в системе, с не существующей парой логин/пароль: эндпоинт логина api/v1/courier/login")
    @Description("Проверка ожидаемого результата: statusCode и body")
    public void loginWithInvalidCredentials() {

        loginCourierRequest = new LoginCourierRequest("123456", "98765");
        Response loginResponse = courierApiClient.loginCourier(loginCourierRequest);
        assertEquals(SC_NOT_FOUND, loginResponse.statusCode());  //statusCode(404)
        String errorMessage = loginResponse.getBody().asString();
        assertTrue(errorMessage.contains("Учетная запись не найдена"));

    }

}


