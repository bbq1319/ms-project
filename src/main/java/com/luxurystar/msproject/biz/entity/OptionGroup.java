package com.luxurystar.msproject.biz.entity;

import java.util.ArrayList;
import java.util.List;

import com.luxurystar.msproject.common.EntityAudit;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OptionGroup extends EntityAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@OneToMany(mappedBy = "optionGroup")
	private List<MenuOptionGroup> menuOptionGroupList = new ArrayList<>();
	@OneToMany(mappedBy = "optionGroup")
	private List<Option> optionList = new ArrayList<>();
}
