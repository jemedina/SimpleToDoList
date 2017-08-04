package com.todolist.model;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ListItemDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Boolean create(ListItem listItem){
        try {
            entityManager.persist(listItem);
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    public List<ListItem> getAllListItems() {
        return entityManager.createQuery("from list_item").getResultList();
    }

    public void updateCheck(long id, boolean newState) {
        ListItem li = entityManager.find(ListItem.class, id);
        li.setState(newState);
        entityManager.merge(li);
    }
}
