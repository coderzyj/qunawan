package com.zyj.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Price entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "price", catalog = "qunawan")
public class Price implements java.io.Serializable {

	// Fields

	private Integer id;
	private Trip trip;
	private Date date;
	private Float price;

	// Constructors

	/** default constructor */
	public Price() {
	}

	/** minimal constructor */
	public Price(Trip trip, Date date) {
		this.trip = trip;
		this.date = date;
	}

	/** full constructor */
	public Price(Trip trip, Date date, Float price) {
		this.trip = trip;
		this.date = date;
		this.price = price;
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
	@JoinColumn(name = "trip", nullable = false)
	public Trip getTrip() {
		return this.trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date", nullable = false, length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "price", precision = 20, scale = 0)
	public Float getPrice() {
		return this.price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

}