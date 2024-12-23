package com.drake7v.accounts.repository;

import com.drake7v.accounts.entity.Accounts;
import com.drake7v.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
        
}
