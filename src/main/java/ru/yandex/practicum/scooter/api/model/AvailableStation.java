package ru.yandex.practicum.scooter.api.model;

public class AvailableStation {
    public AvailableStation() {
    }

    public String name;
    public String number;
    public String color;
    public AvailableStation(String name, String number, String color) {
        this.name = name;
        this.number = number;
        this.color = color;
    }

}
// класс из ListOrderResponse