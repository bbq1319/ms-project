package com.luxurystar.msproject.account.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.luxurystar.msproject.account.entity.Account;
import com.luxurystar.msproject.account.repository.AccountRepository;
import com.luxurystar.msproject.exception.BaseException;
import com.luxurystar.msproject.exception.code.CommonErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {

	private final AccountRepository repository;
	private final PasswordEncoder passwordEncoder;

	public Account register(Account register) {
		if (!repository.findByEmailIgnoreCaseAndUseFlagIsTrue(register.getEmail()).isEmpty()) {
			throw BaseException.from(CommonErrorCode.KEY_ALREADY_IN_USE);
		}

		String password = passwordEncoder.encode(register.getPassword());
		register.setPassword(password);
		return repository.saveAndFlush(register);
	}
}
