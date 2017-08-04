package com.todolist.controller;

import com.todolist.model.ListItem;
import com.todolist.model.ListItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AjaxController {

    @Autowired
    private ListItemDao listItemDao;

    @RequestMapping("/getAllItems")
    @ResponseBody
    public List<ListItem> getAllItems() {
        return listItemDao.getAllListItems();
    }

    @RequestMapping("/createListItem")
    @ResponseBody
    public ListItem createListItem(@RequestParam(value = "title") String title,
                                  @RequestParam(value = "content") String content) {
        ListItem li = new ListItem();
        li.setTitle(title);
        li.setContent(content);
        li.setState(Boolean.FALSE);
        listItemDao.create(li);
        return li;
    }

    @RequestMapping("/updateCheck")
    @ResponseBody
    public void updateCheck(@RequestParam(value = "id") long id,
                            @RequestParam(value = "newState") boolean newState) {
        listItemDao.updateCheck(id,newState);
    }
}
