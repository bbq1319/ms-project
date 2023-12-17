package com.luxurystar.msproject.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.luxurystar.msproject.account.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccountUserDetailsService implements UserDetailsService {

	private final AccountRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return repository.findByEmailIgnoreCaseAndUseFlagIsTrue(email)
			.stream()
			.map(AccountUserDetail::new)
			.findFirst()
			.orElseThrow(() -> new UsernameNotFoundException("계정정보를 찾을 수 없습니다."));
	}
}
