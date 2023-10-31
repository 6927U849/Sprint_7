package ru.yandex.practicum.scooter.api.model;

import org.bouncycastle.crypto.tls.SRTPProtectionProfile;

import java.util.Date;
import java.util.List;

public class OrderResponse {

    public OrderResponse() {
    }

    public OrderResponse(Integer id, Integer courierId, String firstName, String lastName, String address, String metroStation, String phone, String rentTime, String deliveryDate, Integer track, List<String> color, String comment, Date createdAt, Date updatedAt, Integer status) {
        this.id = id;
        this.courierId = courierId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.track = track;
        this.color = color;
        this.comment = comment;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }
    public Integer id;
    public Integer courierId;
    public String firstName;
    public String lastName;
    public String address;
    public String metroStation;
    public String phone;
    public String rentTime;
    public String deliveryDate;
    public Integer track;
    private List<String> color;
    public String comment;
    public Date createdAt;
    public Date updatedAt;
    public Integer status;

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }
}
// класс из ListOrderResponse