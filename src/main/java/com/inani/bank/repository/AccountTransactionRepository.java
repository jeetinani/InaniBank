package com.inani.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inani.bank.domain.Account;
import com.inani.bank.domain.AccountTransaction;


@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction,Long>{
    
    List<AccountTransaction> findByAccount(Account account);
}
