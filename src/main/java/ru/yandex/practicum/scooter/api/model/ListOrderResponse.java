package ru.yandex.practicum.scooter.api.model;


import java.util.List;

public class ListOrderResponse {

    public ListOrderResponse() {
    }

    public ListOrderResponse(List<OrderResponse> orders, PageInfo pageInfo, List<AvailableStation> availableStations) {
        this.orders = orders;
        this.pageInfo = pageInfo;
        this.availableStations = availableStations;
    }

    public List<OrderResponse> orders;
    public PageInfo pageInfo;
    public  List<AvailableStation> availableStations;


    public List<OrderResponse> getOrders() {
        return orders;
    }
}
