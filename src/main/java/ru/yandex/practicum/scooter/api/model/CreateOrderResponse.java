package ru.yandex.practicum.scooter.api.model;
import java.util.List;
import org.springframework.core.annotation.Order;


public class CreateOrderResponse {
    public Integer track;


    public CreateOrderResponse(Integer track) {
        this.track = track;

    }

    public CreateOrderResponse() {
    }
}
