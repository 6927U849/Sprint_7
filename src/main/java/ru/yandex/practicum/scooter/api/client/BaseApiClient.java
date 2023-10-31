package ru.yandex.practicum.scooter.api.client;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class BaseApiClient {
public RequestSpecification getPostSpec() {
    return given()
            .filter(new ResponseLoggingFilter())
            .filter(new RequestLoggingFilter())     // можно включить .log().all() и после then
            .contentType(ContentType.JSON);         //.header("Content-type", "application/json")
}

}

// в данном классе хранится клиент для обращение к приложению