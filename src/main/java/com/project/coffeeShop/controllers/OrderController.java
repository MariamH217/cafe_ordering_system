package com.project.coffeeShop.controllers;

import com.project.coffeeShop.dao.OrderDAO;
import com.project.coffeeShop.dao.OrderDetailsDAO;
import com.project.coffeeShop.models.Order;
import com.project.coffeeShop.models.OrderDetails;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderDAO orderDAO;
    private final OrderDetailsDAO orderDetailsDAO;

    @Autowired
    public OrderController(OrderDAO orderDAO, OrderDetailsDAO orderDetailsDAO) {
        this.orderDAO = orderDAO;
        this.orderDetailsDAO = orderDetailsDAO;
    }

    @PostMapping("/place")
    public String placeOrder(@ModelAttribute("order") @Valid Order order, BindingResult bindingResult,
                             @ModelAttribute("orderDetails") @Valid OrderDetails orderDetails, BindingResult detailsBindingResult) {
        if (bindingResult.hasErrors() || detailsBindingResult.hasErrors())
            return "order/placeOrder";

        orderDAO.saveOrder(order);

        orderDetails.setOrderId(order.getOrderId());

        orderDetailsDAO.saveOrderDetails(orderDetails);

        return "redirect:/successOrder";
    }

    @GetMapping("/view/{orderId}")
    public String viewOrder(@PathVariable("orderId") int orderId, Model model) {

        Order order = orderDAO.getOrderById(orderId);
        OrderDetails orderDetails = orderDetailsDAO.getOrderDetailsByOrderId(orderId);


        model.addAttribute("order", order);
        model.addAttribute("orderDetails", orderDetails);


        return "order/view";
    }

    @GetMapping("/update/{orderId}")
    public String showUpdateForm(@PathVariable("orderId") int orderId, Model model) {
        Order order = orderDAO.getOrderById(orderId);
        OrderDetails orderDetails = orderDetailsDAO.getOrderDetailsById(orderId); // Assuming you have a method to fetch OrderDetails by OrderId
        model.addAttribute("order", order);
        model.addAttribute("orderDetails", orderDetails);
        return "order/edit";
    }

    @PostMapping("/update/{orderId}")
    public String updateOrder(@PathVariable("orderId") int orderId, @ModelAttribute("order") @Valid Order order,
                              @ModelAttribute("orderDetails") @Valid OrderDetails orderDetails,
                              BindingResult orderBindingResult, BindingResult detailsBindingResult) {
        if (orderBindingResult.hasErrors() || detailsBindingResult.hasErrors())
            return "order/edit";

        order.setOrderId(orderId);
        orderDAO.updateOrder(order);

        orderDetails.setOrderId(orderId);
        orderDetailsDAO.updateOrderDetails(orderDetails);

        return "redirect:/order/view/{orderId}";
    }


    @GetMapping("/cancel/{orderId}")
    public String deleteMenuItem(@PathVariable("orderId") int orderId) {
        orderDAO.cancelOrder(orderId);
        orderDetailsDAO.removeOrderDetails(orderId);
        return "redirect:/orders";
    }



}


