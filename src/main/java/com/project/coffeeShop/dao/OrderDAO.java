package com.project.coffeeShop.dao;

import com.project.coffeeShop.models.MenuItem;
import com.project.coffeeShop.models.Order;
import com.project.coffeeShop.models.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public class OrderDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Order> getOrders() {
        String sql = "SELECT * FROM orders";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Order.class));
    }

    public void saveOrder(Order order) {
        String sql = "INSERT INTO orders (waiter_id, table_number,order_time,is_finalized) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, order.getWaiterId(), order.getTableNumber(),order.getOrderTime(),order.getFinalized());
    }

    public void updateOrder(Order order){
        String sql = "UPDATE orders SET waiter_id = ?, table_number = ?,order_time = ?,is_finalized = ?";
        jdbcTemplate.update(sql,order.getWaiterId(),order.getTableNumber(),order.getOrderTime(),order.getFinalized());

    }


    public void cancelOrder(int orderId) {
        String sql = "UPDATE orders SET is_finalized = FALSE WHERE order_id = ?";
        jdbcTemplate.update(sql, orderId);
    }

    public List<Order> getOrdersByDateRange(LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT * FROM orders WHERE order_time BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, new Object[]{startDate, endDate}, new BeanPropertyRowMapper<>(Order.class));
    }

    public Order getOrderById(int orderId) {
        String sql = "SELECT * FROM order WHERE order_id = ?";
        return jdbcTemplate.query(sql,new Object[]{orderId},new BeanPropertyRowMapper<>(Order.class)).stream().findAny().orElse(null);

    }
}
