package com.gmail.arthurstrokov.dao.impl;

import com.gmail.arthurstrokov.dao.BuisnessCardDao;
import com.gmail.arthurstrokov.dao.model.BuisnessCard;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("buisnessCardDao")
@SuppressWarnings("unchecked")
public class BuisnessCardDaoImpl extends GenericDaoImpl<BuisnessCard> implements BuisnessCardDao {

    public BuisnessCardDaoImpl() {
        super(BuisnessCard.class);
    }

    @Override
    public void deleteById(BuisnessCard entityId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<BuisnessCard> findBuisnessCardsByUserId(Long userId) {
        String hql = "FROM BuisnessCard as b WHERE b.user.id=:userId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userId", userId);
        return query.list();
    }
}
