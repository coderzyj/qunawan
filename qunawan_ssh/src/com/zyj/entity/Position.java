package com.zyj.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Position entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "position", catalog = "qunawan")
public class Position implements java.io.Serializable {

	// Fields

	private Integer id;
	private Float p1;
	private Float p2;
	private Integer level;
	private Set<Trip> trips = new HashSet<Trip>(0);
	private Set<Trip> trips_1 = new HashSet<Trip>(0);

	// Constructors

	/** default constructor */
	public Position() {
	}

	/** minimal constructor */
	public Position(Float p1, Float p2, Integer level) {
		this.p1 = p1;
		this.p2 = p2;
		this.level = level;
	}

	/** full constructor */
	public Position(Float p1, Float p2, Integer level, Set<Trip> trips,
			Set<Trip> trips_1) {
		this.p1 = p1;
		this.p2 = p2;
		this.level = level;
		this.trips = trips;
		this.trips_1 = trips_1;
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

	@Column(name = "p1", nullable = false, precision = 10, scale = 6)
	public Float getP1() {
		return this.p1;
	}

	public void setP1(Float p1) {
		this.p1 = p1;
	}

	@Column(name = "p2", nullable = false, precision = 10, scale = 6)
	public Float getP2() {
		return this.p2;
	}

	public void setP2(Float p2) {
		this.p2 = p2;
	}

	@Column(name = "level", nullable = false)
	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "position")
	public Set<Trip> getTrips() {
		return this.trips;
	}

	public void setTrips(Set<Trip> trips) {
		this.trips = trips;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "position")
	public Set<Trip> getTrips_1() {
		return this.trips_1;
	}

	public void setTrips_1(Set<Trip> trips_1) {
		this.trips_1 = trips_1;
	}

}