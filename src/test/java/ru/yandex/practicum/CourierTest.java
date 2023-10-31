package ru.yandex.practicum;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.scooter.api.client.CourierApiClient;
import ru.yandex.practicum.scooter.api.heiper.CourierGenerator;
import ru.yandex.practicum.scooter.api.model.CreateCourierRequest;
import ru.yandex.practicum.scooter.api.model.CreateCourierResponse;


import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.*;


public class CourierTest {
    CreateCourierRequest createCourierRequest;
    CourierApiClient courierApiClient;

    @Before
    public void setup() {
        courierApiClient = new CourierApiClient();
        createCourierRequest = CourierGenerator.getRandomCourier();

    }

    @Test
    @DisplayName("Создание курьера: эндпоинт создания api/v1/courier")
    @Description("Проверка ожидаемого результата: statusCode и body")
    public void createCourier() {
        Response createResponse = courierApiClient.createCourier(createCourierRequest);
        assertEquals(SC_CREATED, createResponse.statusCode());    //statusCode(201)
        CreateCourierResponse createCourierResponse = createResponse.as(CreateCourierResponse.class);
        assertTrue(createCourierResponse.ok);
    }


    @Test
    @DisplayName("Создание курьера без пароля: эндпоинт логина api/v1/courier")
    @Description("Проверка ожидаемого результата: statusCode и body")
    public void createCourierWithoutPassword() {
        CreateCourierRequest incompleteRequest = new CreateCourierRequest("login", null, "firstName");
        Response createResponse = courierApiClient.createCourier(incompleteRequest);
        assertEquals(HttpStatus.SC_BAD_REQUEST, createResponse.statusCode()); // statusCode(400)
        assertEquals("Недостаточно данных для создания учетной записи", createResponse.jsonPath().getString("message"));
    }

    @Test
    @DisplayName("Создание курьера без имени: эндпоинт логина api/v1/courier")
    @Description("Проверка ожидаемого результата: statusCode и body")
    public void createCourierWithoutFirstName() {
        CreateCourierRequest incompleteRequest = new CreateCourierRequest("login", "password", null);
        Response createResponse = courierApiClient.createCourier(incompleteRequest);
        assertEquals(SC_CONFLICT, createResponse.statusCode()); // statusCode(409)
        assertEquals("Этот логин уже используется. Попробуйте другой.", createResponse.jsonPath().getString("message"));
    }

    @Test
    @DisplayName("Создание курьера без логина: эндпоинт логина api/v1/courier")
    @Description("Проверка ожидаемого результата: statusCode и body")
    public void createCourierWithoutLogin() {
        CreateCourierRequest incompleteRequest = new CreateCourierRequest(null, "password", "firstName");
        Response createResponse = courierApiClient.createCourier(incompleteRequest);
        assertEquals(HttpStatus.SC_BAD_REQUEST, createResponse.statusCode()); // statusCode(400)
        assertEquals("Недостаточно данных для создания учетной записи", createResponse.jsonPath().getString("message"));
    }


    @Test
    @DisplayName("Создание двух одинаковых курьеров: эндпоинт логина api/v1/courier")
    @Description("Проверка ожидаемого результата: statusCode и body")
    public void courierTestSameUserName() {
        Response createResponse = courierApiClient.createCourier(createCourierRequest);
        assertEquals(SC_CREATED, createResponse.statusCode());    //statusCode(201)
        CreateCourierResponse createCourierResponse = createResponse.as(CreateCourierResponse.class);
        assertTrue(createCourierResponse.ok);
        Response duplicateCreateResponse = courierApiClient.createCourier(createCourierRequest);
        assertEquals(SC_CONFLICT, duplicateCreateResponse.statusCode()); // statusCode(409)
        assertEquals("Этот логин уже используется. Попробуйте другой.", duplicateCreateResponse.jsonPath().getString("message"));
    }
}


