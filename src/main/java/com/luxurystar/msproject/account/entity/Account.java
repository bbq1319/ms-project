package com.luxurystar.msproject.account.entity;

import com.luxurystar.msproject.account.AccountRole;
import com.luxurystar.msproject.common.EntityAudit;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class Account extends EntityAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, updatable = false, length = 300)
	private String email;
	@Column(nullable = false, length = 60)
	private String password;
	@Column(nullable = false, length = 30)
	private String name;
	@Column(nullable = false)
	@Convert(converter = AccountRole.Converter.class)
	private AccountRole role;
	@Column(nullable = false)
	private boolean useFlag = true;
}
