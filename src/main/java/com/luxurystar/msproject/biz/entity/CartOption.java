package com.luxurystar.msproject.biz.entity;

import com.luxurystar.msproject.common.EntityBaseAudit;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CartOption extends EntityBaseAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;
	@ManyToOne
	@JoinColumn(name = "option_id")
	private Option option;
}
