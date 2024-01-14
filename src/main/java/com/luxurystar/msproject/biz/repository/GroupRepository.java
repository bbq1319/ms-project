package com.luxurystar.msproject.biz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luxurystar.msproject.biz.entity.MenuGroup;

@Repository
public interface GroupRepository extends JpaRepository<MenuGroup, Long> {
}
