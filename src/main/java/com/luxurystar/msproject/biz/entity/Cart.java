package com.luxurystar.msproject.biz.entity;

import java.util.ArrayList;
import java.util.List;

import com.luxurystar.msproject.account.entity.Account;
import com.luxurystar.msproject.common.EntityBaseAudit;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cart extends EntityBaseAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;
	@OneToOne
	@JoinColumn(name = "menu_id")
	private Menu menu;
	private int count;

	@OneToMany(mappedBy = "cart")
	private List<CartOption> cartOptionList = new ArrayList<>();
}
