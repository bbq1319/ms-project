package com.luxurystar.msproject.biz.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luxurystar.msproject.biz.controller.response.MenuGroupListResponse;
import com.luxurystar.msproject.biz.service.MenuGroupService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu-group")
public class MenuGroupController {

	private final MenuGroupService service;
	private final ModelMapper mapper;

	@GetMapping
	public List<MenuGroupListResponse> getList() {
		return service.getList()
			.stream()
			.map(each -> mapper.map(each, MenuGroupListResponse.class))
			.toList();
	}
}
