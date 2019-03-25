package com.gmail.arthurstrokov.dao.impl;

import com.gmail.arthurstrokov.dao.ItemDao;
import com.gmail.arthurstrokov.dao.model.Item;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("itemDao")
public class ItemDaoImpl extends GenericDaoImpl<Item> implements ItemDao {

    public ItemDaoImpl() {
        super(Item.class);
    }

    @Override
    public void deleteById(Item entityId) {
        throw new UnsupportedOperationException();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Item> getAvailableItemsForPage(Long page) {
        String selectPage = "FROM Item as i WHERE i.isAlive=true order by i.name desc";
        Query query = getCurrentSession().createQuery(selectPage);
        query.setFirstResult((int) ((page - 1) * 5));
        query.setMaxResults((int) (page * 5));
        return query.list();
    }

    @Override
    public Long countAvailableItems() {
        String hql = "select count(*) from Item as i where i.isAlive=true";
        Query query = getCurrentSession().createQuery(hql);
        return (Long) query.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Item> getAllItemsForPage(Long page) {
        String hql = "FROM Item as i order by i.name";
        Query query = getCurrentSession().createQuery(hql);
        query.setFirstResult((int) ((page - 1) * 5));
        query.setMaxResults((int) (page * 5));
        return query.list();
    }

    @Override
    public Long countAllItems() {
        String hql = "select count(*) from Item as i";
        Query query = getCurrentSession().createQuery(hql);
        return (Long) query.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Item findBy(String uniqueNumber) {
        String hql = "FROM Item as i WHERE i.uniqueNumber=:uniqueNumber";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("uniqueNumber", uniqueNumber);
        return (Item) query.getSingleResult();
    }
}
