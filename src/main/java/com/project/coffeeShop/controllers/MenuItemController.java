package com.project.coffeeShop.controllers;


import com.project.coffeeShop.dao.MenuItemDAO;
import com.project.coffeeShop.models.MenuItem;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/menu")
public class MenuItemController {
    private final MenuItemDAO menuItemDAO;

    @Autowired
    public MenuItemController(MenuItemDAO menuItemDAO) {
        this.menuItemDAO = menuItemDAO;
    }

    @GetMapping()
    public String getAllMenuItems(Model model) {
        model.addAttribute("menuItems", menuItemDAO.getMenuItems());
        return "menu/showMenu";
    }

    @PostMapping()
    public String addMenuItem(@ModelAttribute("menuItem") @Valid MenuItem menuItem, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "views/menu/new";

        menuItemDAO.saveMenuItem(menuItem);
        return "redirect:/menu/showMenu";
    }

    @GetMapping("/{itemId}/update")
    public String showUpdateForm( Model model,@PathVariable("itemId") int itemId) {
        MenuItem menuItem = menuItemDAO.getMenuItemById(itemId);
        model.addAttribute("menuItem", menuItem);
        return "menu/edit";
    }

    @PostMapping("/update/{itemId}")
    public String updateMenuItem(@ModelAttribute("menuItem") @Valid MenuItem menuItem, BindingResult bindingResult, @PathVariable("itemId") int itemId) {
        if (bindingResult.hasErrors())
            return "menu/edit";

        menuItem.setItemId(itemId);
        menuItemDAO.updateMenuItem(menuItem);
        return "redirect:/menu/showMenu";
    }

    @DeleteMapping("/delete/{itemId}")
    public String deleteMenuItem(@PathVariable("itemId") int itemId) {
        menuItemDAO.removeMenuItem(itemId);
        return "redirect:/menu/showMenu";
    }
}

