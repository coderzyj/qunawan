package com.zyj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Ordercontact entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ordercontact", catalog = "qunawan")
public class Ordercontact implements java.io.Serializable {

	// Fields

	private Integer id;
	private Orders orders;
	private Contact contact;
	private Integer type;

	// Constructors

	/** default constructor */
	public Ordercontact() {
	}

	/** minimal constructor */
	public Ordercontact(Orders orders, Contact contact) {
		this.orders = orders;
		this.contact = contact;
	}

	/** full constructor */
	public Ordercontact(Orders orders, Contact contact, Integer type) {
		this.orders = orders;
		this.contact = contact;
		this.type = type;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orders", nullable = false)
	public Orders getOrders() {
		return this.orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contact", nullable = false)
	public Contact getContact() {
		return this.contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}