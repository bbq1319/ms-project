package com.luxurystar.msproject.biz.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class ProductTagId implements Serializable {

	@Column(name = "product_id")
	private Long productId;
	@Column(name = "tag_id")
	private Long tagId;

	public ProductTagId(Long productId, Long tagId) {
		this.productId = productId;
		this.tagId = tagId;
	}
}
