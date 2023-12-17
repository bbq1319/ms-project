package com.luxurystar.msproject.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luxurystar.msproject.account.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	List<Account> findByEmailIgnoreCaseAndUseFlagIsTrue(String email);
}
