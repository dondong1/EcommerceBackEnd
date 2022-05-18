package com.ecommerce.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private Double price;

	private int quantity;

	private String pictureUrl;

	@JsonBackReference(value = "user")
	@ManyToOne
	private User user;


	@OneToMany(fetch = FetchType.LAZY)
	private List<Product> product;

	public Cart() {
		super();
	}

	public Cart(String name, Double price, int quantity, String pictureUrl, User user) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.pictureUrl = pictureUrl;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public void add(Cart cart) {
	}

	public void remove(Cart cart) {
	}
}
