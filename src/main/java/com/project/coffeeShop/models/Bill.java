package com.project.coffeeShop.models;

public class Bill {

    private Integer billId;
    private Integer orderId;
    private Double subtotal;
    private Double serviceFee;
    private Double tax;
    private Double tip;
    private Double total;

    public Bill(Integer orderId, Double subtotal, Double serviceFee, Double tax, Double total) {
        this.orderId = orderId;
        this.subtotal = subtotal;
        this.serviceFee = serviceFee;
        this.tax = tax;
        this.total = total;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getTip() {
        return tip;
    }

    public void setTip(Double tip) {
        this.tip = tip;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}