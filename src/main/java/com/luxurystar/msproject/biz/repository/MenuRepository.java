package com.luxurystar.msproject.biz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luxurystar.msproject.biz.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
