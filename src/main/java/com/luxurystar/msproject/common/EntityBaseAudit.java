package com.luxurystar.msproject.common;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class EntityBaseAudit {

	@Column(nullable = false, updatable = false)
	@CreatedDate
	protected LocalDateTime createdDate = LocalDateTime.now();
	@Column(nullable = false, updatable = false)
	@CreatedBy
	protected String createdBy = "SYSTEM";
}
