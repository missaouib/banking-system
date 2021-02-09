package com.ironhack.bankingsystem.repository.transaction;

import com.ironhack.bankingsystem.model.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query(value = "select count(*),origin_account_id, creation_date from transaction WHERE creation_date < CURDATE() " +
            " group by origin_account_id, DAY(creation_date) order by count(*) desc LIMIT 1",nativeQuery = true)
    public Object[][] getMaximumTransactionInDay();


    @Query(value = "select count(*),origin_account_id, creation_date from transaction WHERE creation_date > CURDATE() " +
            "and origin_account_id = :accountId group by origin_account_id, DAY(creation_date) LIMIT 1",nativeQuery = true)
    public Object[][] getTransactionByOriginAccount(Integer accountId);



}
