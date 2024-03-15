package com.project.coffeeShop.controllers;


import com.project.coffeeShop.dao.BillDAO;
import com.project.coffeeShop.models.Bill;
import com.project.coffeeShop.services.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/billing")
public class BillController {
    private final BillDAO billDAO;
    private final BillingService billingService;

    @Autowired
    public BillController(BillDAO billDAO, BillingService billingService) {
        this.billDAO = billDAO;
        this.billingService = billingService;
    }

    @GetMapping("/generate/{orderId}")
    public String generateBillForOrder(@PathVariable("orderId") int orderId) {
        billingService.generateBillForOrder(orderId);
        return "redirect:/billing/view/{orderId}";
    }

    @GetMapping("/view/{orderId}")
    public String viewBill(@PathVariable("orderId") int orderId, Model model) {
        Bill bill = billDAO.getBillById(orderId);
        if (bill == null) {
            return "error";
        }
        model.addAttribute("bill", bill);
        return "billing/viewBill";
    }
}
