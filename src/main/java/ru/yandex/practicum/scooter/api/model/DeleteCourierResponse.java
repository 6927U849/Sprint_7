package ru.yandex.practicum.scooter.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteCourierResponse {
    public DeleteCourierResponse() {
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    @JsonProperty("ok")
    public Boolean ok;
    @JsonProperty("message")
    public String message;

    public DeleteCourierResponse(Boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }

}
