package com.project.coffeeShop.models;

import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public class Order {
    private Integer orderId;

    @NotNull(message = "Waiter ID is required")
    private Integer waiterId;

    @NotNull(message = "Table number is required")
    private Integer tableNumber;

    @NotNull(message = "Order time is required")
    private Timestamp orderTime;

    @NotNull(message = "Finalized status is required")
    private Boolean isFinalized;
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(Integer waiterId) {
        this.waiterId = waiterId;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public Boolean getFinalized() {
        return isFinalized;
    }

    public void setFinalized(Boolean finalized) {
        isFinalized = finalized;
    }
}