package com.luxurystar.msproject.biz.entity;

import java.util.ArrayList;
import java.util.List;

import com.luxurystar.msproject.common.EntityAudit;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Menu extends EntityAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Long price;
	@ManyToOne
	@JoinColumn(name = "menu_group_id")
	private MenuGroup menuGroup;
	private boolean showFlag;

	@OneToMany(mappedBy = "menu")
	private List<MenuOptionGroup> menuOptionGroupList = new ArrayList<>();
}
