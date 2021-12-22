package com.trendyol.linkconverter.repository;

import com.trendyol.linkconverter.entity.TransactionHistory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Repository
public class TransactionHistoryRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(TransactionHistory transactionHistory) {
        String id = transactionHistory.getId();

        if(StringUtils.isEmpty(id)) {
            transactionHistory.setId(UUID.randomUUID().toString());
            entityManager.persist(transactionHistory);
        }
        else {
            entityManager.merge(transactionHistory);
        }
    }
}
