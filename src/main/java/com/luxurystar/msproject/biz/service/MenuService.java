package com.luxurystar.msproject.biz.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luxurystar.msproject.biz.entity.Menu;
import com.luxurystar.msproject.biz.repository.MenuRepository;
import com.luxurystar.msproject.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuService {

	private final MenuRepository repository;

	public Menu getOne(Long id) {
		return repository.findById(id).orElseThrow(NotFoundException::new);
	}
}
