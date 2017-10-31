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
 * Themeontrip entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "themeontrip", catalog = "qunawan")
public class Themeontrip implements java.io.Serializable {

	// Fields

	private Integer id;
	private Trip trip;
	private Theme theme;

	// Constructors

	/** default constructor */
	public Themeontrip() {
	}

	/** full constructor */
	public Themeontrip(Trip trip, Theme theme) {
		this.trip = trip;
		this.theme = theme;
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
	@JoinColumn(name = "theme", nullable = false)
	public Theme getTheme() {
		return this.theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

}