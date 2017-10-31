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
 * Detail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "detail", catalog = "qunawan")
public class Detail implements java.io.Serializable {

	// Fields

	private Integer id;
	private Trip trip;
	private String place;
	private String hotel;
	private String food;

	// Constructors

	/** default constructor */
	public Detail() {
	}

	/** minimal constructor */
	public Detail(Trip trip, String place) {
		this.trip = trip;
		this.place = place;
	}

	/** full constructor */
	public Detail(Trip trip, String place, String hotel, String food) {
		this.trip = trip;
		this.place = place;
		this.hotel = hotel;
		this.food = food;
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

	@Column(name = "place", nullable = false, length = 1000)
	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@Column(name = "hotel", length = 1000)
	public String getHotel() {
		return this.hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	@Column(name = "food", length = 1000)
	public String getFood() {
		return this.food;
	}

	public void setFood(String food) {
		this.food = food;
	}

}