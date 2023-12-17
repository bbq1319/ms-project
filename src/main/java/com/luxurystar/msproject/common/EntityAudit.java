package com.luxurystar.msproject.common;

import java.time.LocalDateTime;

import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
public class EntityAudit extends EntityBaseAudit {

	@Column(nullable = false)
	@LastModifiedDate
	protected LocalDateTime modifiedDate = LocalDateTime.now();
	@Column(nullable = false)
	@LastModifiedBy
	protected String modifiedBy = "SYSTEM";
}
