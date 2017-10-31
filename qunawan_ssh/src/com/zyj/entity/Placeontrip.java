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
 * Placeontrip entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "placeontrip", catalog = "qunawan")
public class Placeontrip implements java.io.Serializable {

	// Fields

	private Integer id;
	private Trip trip;
	private Place place;

	// Constructors

	/** default constructor */
	public Placeontrip() {
	}

	/** full constructor */
	public Placeontrip(Trip trip, Place place) {
		this.trip = trip;
		this.place = place;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "place", nullable = false)
	public Place getPlace() {
		return this.place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

}