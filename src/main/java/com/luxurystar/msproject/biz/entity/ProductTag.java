package com.luxurystar.msproject.biz.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Getter;
import lombok.Setter;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class ProductTag implements Persistable<ProductTagId> {

	@EmbeddedId
	private ProductTagId id;
	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name = "product_id")
	private Product product;
	@ManyToOne
	@JoinColumn(name = "tag_id")
	private Tag tag;
	@CreatedDate
	private LocalDate created;

	@Override
	public boolean isNew() {
		return created == null;
	}

	public void setParent(Product product) {
		this.product = product;
		this.id = new ProductTagId(product.getId(), tag.getId());
	}
}
