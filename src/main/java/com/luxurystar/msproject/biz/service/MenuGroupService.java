package com.luxurystar.msproject.biz.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luxurystar.msproject.biz.entity.MenuGroup;
import com.luxurystar.msproject.biz.repository.GroupRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuGroupService {

	private final GroupRepository repository;

	public List<MenuGroup> getList() {
		return repository.findAll();
	}
}
