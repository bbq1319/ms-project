package com.luxurystar.msproject.security;

import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.luxurystar.msproject.account.entity.Account;

import lombok.Getter;

@Getter
public class AccountUserDetail extends User {

	private final Account account;

	public AccountUserDetail(Account account) {
		super(account.getEmail(), account.getPassword(), account.isUseFlag(), true, true, true,
			Set.of(new SimpleGrantedAuthority(account.getRole().name())));
		this.account = account;
	}
}
