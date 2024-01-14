package com.luxurystar.msproject.biz.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luxurystar.msproject.biz.controller.response.MenuDetailResponse;
import com.luxurystar.msproject.biz.service.MenuService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

	private final MenuService service;
	private final ModelMapper mapper;

	@GetMapping("/{id}")
	public MenuDetailResponse getOne(@PathVariable("id") Long id) {
		return mapper.map(service.getOne(id), MenuDetailResponse.class);
	}
}
