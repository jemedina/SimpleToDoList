package com.todolist.controller;

import com.todolist.model.ListItem;
import com.todolist.model.ListItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ListController {

    @Autowired
    private ListItemDao listItemDao;

    @RequestMapping("/")
    public String index(Model model) {
        List<ListItem> listItems = listItemDao.getAllListItems();
        model.addAttribute("listItems", listItems);
        return "list";
    }
}
