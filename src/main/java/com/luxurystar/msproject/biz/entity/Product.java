package com.luxurystar.msproject.biz.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductTag> tag = new ArrayList<>();

	public void setTag(List<ProductTag> list) {
		for (ProductTag productTag : list) {
			if (!this.tag.contains(productTag)) {
				this.tag.add(productTag);
				productTag.setParent(this);
			}
		}
	}
}
