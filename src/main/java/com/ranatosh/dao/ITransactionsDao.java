package com.ranatosh.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ranatosh.entityModel.TransactionsModel;

public interface ITransactionsDao extends JpaRepository<TransactionsModel, Long> {

    List<TransactionsModel> findByContactOrderByTxnTimeDesc(long contact);
}
