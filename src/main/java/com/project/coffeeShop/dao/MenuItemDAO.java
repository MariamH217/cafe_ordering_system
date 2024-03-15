package com.project.coffeeShop.dao;

import com.project.coffeeShop.models.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuItemDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MenuItemDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<MenuItem> getMenuItems() {
        String sql = "SELECT * FROM menu_items";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MenuItem.class));
    }

    public MenuItem getMenuItemById(int itemId) {
        String sql = "SELECT * FROM menu_items WHERE item_id = ?";
        return jdbcTemplate.query(sql, new Object[]{itemId}, new BeanPropertyRowMapper<>(MenuItem.class)).stream().findAny().orElse(null);
    }

    public void saveMenuItem(MenuItem menuItem) {
        String sql = "INSERT INTO menu_items (name, description, price, category) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, menuItem.getName(), menuItem.getDescription(), menuItem.getPrice(), menuItem.getCategory());
    }

    public void updateMenuItem(MenuItem menuItem) {
        String sql = "UPDATE menu_items SET name = ?, description = ?, price = ?, category = ? WHERE item_id = ?";
        jdbcTemplate.update(sql, menuItem.getName(), menuItem.getDescription(), menuItem.getPrice(), menuItem.getCategory(), menuItem.getItemId());
    }

    public void removeMenuItem(int itemId) {
        String sql = "DELETE FROM menu_items WHERE item_id = ?";
        jdbcTemplate.update(sql, itemId);
    }
}
