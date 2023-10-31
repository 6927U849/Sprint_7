package ru.yandex.practicum.scooter.api.model;

public class PageInfo {
    public int page;
    public int total;
    public int limit;

    public PageInfo() {
    }

    public PageInfo(int page, int total, int limit) {
        this.page = page;
        this.total = total;
        this.limit = limit;
    }

}
// класс из ListOrderResponse