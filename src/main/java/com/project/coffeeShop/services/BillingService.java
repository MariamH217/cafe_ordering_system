package com.project.coffeeShop.services;

import com.project.coffeeShop.dao.BillDAO;
import com.project.coffeeShop.dao.OrderDAO;
import com.project.coffeeShop.dao.OrderDetailsDAO;
import com.project.coffeeShop.models.Bill;

import com.project.coffeeShop.models.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BillingService {

    private final OrderDAO orderDAO;
    private final BillDAO billDAO;
    private final OrderDetailsDAO orderDetailsDAO;
    private static final double SERVICE_FEE_PERCENTAGE = 0.1;
    private static final double TAX_PERCENTAGE = 0.2;
    private static final double TIP_PERCENTAGE = 0.1;



    @Autowired
    public BillingService(OrderDAO orderDAO, BillDAO billDAO, OrderDetailsDAO orderDetailsDAO) {
        this.orderDAO = orderDAO;
        this.billDAO = billDAO;
        this.orderDetailsDAO = orderDetailsDAO;
    }

    public void generateBillForOrder(int orderId) {

        OrderDetails orderDetails = orderDetailsDAO.getOrderDetailsById(orderId);

        double subtotal = calculateSubtotal(orderDetails);
        double serviceFee = calculateServiceFee(subtotal);
        double tax = calculateTax(subtotal);
        double tip = calculateTip(subtotal);
        double total = subtotal + serviceFee + tax + tip;

        Bill bill = new Bill(orderId, subtotal, serviceFee, tax, total);

        billDAO.saveBill(bill);
    }

    private double calculateSubtotal(OrderDetails orderDetails) {
        return orderDetails.getQuantity() * orderDetails.getPriceAtTime();
    }

    private double calculateServiceFee(double subtotal) {
        return subtotal * SERVICE_FEE_PERCENTAGE;
    }

    private double calculateTax(double subtotal) {
        return subtotal * TAX_PERCENTAGE;
    }

    private double calculateTip(double subtotal) {
        return subtotal * TIP_PERCENTAGE;
    }

}

