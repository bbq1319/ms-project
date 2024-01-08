package com.luxurystar.msproject.account.controller;

import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luxurystar.msproject.account.entity.Account;
import com.luxurystar.msproject.account.resource.request.RegisterRequest;
import com.luxurystar.msproject.account.service.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

	private final AccountService service;
	private final ModelMapper mapper;

	@PostMapping
	public Long register(@Validated @RequestBody RegisterRequest request) {
		return service.register(mapper.map(request, Account.class)).getId();
	}
}
