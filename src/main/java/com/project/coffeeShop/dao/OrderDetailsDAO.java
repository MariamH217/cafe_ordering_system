package com.project.coffeeShop.dao;

import com.project.coffeeShop.models.MenuItem;
import com.project.coffeeShop.models.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDetailsDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderDetailsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<OrderDetails> getOrderDetails(){
        String sql = "SELECT * FROM order_details";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OrderDetails.class));
    }

    public OrderDetails getOrderDetailsById(int orderDetailsId){
        String sql = "SELECT * FROM order_details WHERE order_details_id = ?";
        return jdbcTemplate.query(sql,new Object[]{orderDetailsId},new BeanPropertyRowMapper<>(OrderDetails.class)).stream().findAny().orElse(null);
    }


    public void saveOrderDetails(OrderDetails orderDetails) {
        String sql = "INSERT INTO order_details (orderId, itemId,quantity,priceAtTime) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, orderDetails.getOrderId(), orderDetails.getItemId(), orderDetails.getQuantity(), orderDetails.getPriceAtTime());
    }

    public void updateOrderDetails(OrderDetails orderDetails){
        String sql = "UPDATE order_details SET orderId = ?, itemId = ?, quantity = ?, priceAtTime = ?";
        jdbcTemplate.update(sql,orderDetails.getOrderId(), orderDetails.getItemId(), orderDetails.getQuantity(), orderDetails.getPriceAtTime());
    }

    public void removeOrderDetails(int orderDetailId) {
        String sql = "DELETE FROM order_details WHERE order_detail_id = ?";
        jdbcTemplate.update(sql, orderDetailId);
    }

    public OrderDetails getOrderDetailsByOrderId(int orderDetailId) {
        String sql = "SELECT * FROM order_details WHERE order_detail_id = ?";
        return jdbcTemplate.query(sql,new Object[]{orderDetailId},new BeanPropertyRowMapper<>(OrderDetails.class)).stream().findAny().orElse(null);


    }
}
