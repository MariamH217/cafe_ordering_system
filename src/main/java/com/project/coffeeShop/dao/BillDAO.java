package com.project.coffeeShop.dao;

import com.project.coffeeShop.models.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BillDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BillDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Bill> getBills() {
        String sql = "SELECT * FROM bills";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Bill.class));
    }

    public Bill getBillById(int billId) {
        String sql = "SELECT * FROM bills WHERE bill_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{billId}, new BeanPropertyRowMapper<>(Bill.class));
    }

    public void saveBill(Bill bill) {
        String sql = "INSERT INTO bills (order_id, subtotal, service_fee, tax, tip, total) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, bill.getOrderId(), bill.getSubtotal(), bill.getServiceFee(), bill.getTax(), bill.getTip(), bill.getTotal());
    }

    public void updateBill(Bill bill) {
        String sql = "UPDATE bills SET subtotal = ?, service_fee = ?, tax = ?, tip = ?, total = ? WHERE bill_id = ?";
        jdbcTemplate.update(sql, bill.getSubtotal(), bill.getServiceFee(), bill.getTax(), bill.getTip(), bill.getTotal(), bill.getBillId());
    }

    public void removeBill(int billId) {
        String sql = "DELETE FROM bills WHERE bill_id = ?";
        jdbcTemplate.update(sql, billId);
    }


}
