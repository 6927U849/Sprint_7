package ru.yandex.practicum.scooter.api.model;
import com.fasterxml.jackson.annotation.JsonProperty;


public class LoginCourierResponse {
    public LoginCourierResponse() {
    }
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("message")
    public String message;
    public LoginCourierResponse(Integer id, String message) {
        this.id = id;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
