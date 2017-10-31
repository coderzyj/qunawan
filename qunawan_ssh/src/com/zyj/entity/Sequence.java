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
 * Sequence entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sequence", catalog = "qunawan")
public class Sequence implements java.io.Serializable {

	// Fields

	private Integer id;
	private String value;
	private String keying;
	private String type;
	private String descing;
	private Set<Orders> orderses = new HashSet<Orders>(0);
	private Set<Trip> trips = new HashSet<Trip>(0);
	private Set<Orders> orderses_1 = new HashSet<Orders>(0);
	private Set<Trip> trips_1 = new HashSet<Trip>(0);

	// Constructors

	/** default constructor */
	public Sequence() {
	}

	/** minimal constructor */
	public Sequence(String value, String keying) {
		this.value = value;
		this.keying = keying;
	}

	/** full constructor */
	public Sequence(String value, String keying, String type, String descing,
			Set<Orders> orderses, Set<Trip> trips, Set<Orders> orderses_1,
			Set<Trip> trips_1) {
		this.value = value;
		this.keying = keying;
		this.type = type;
		this.descing = descing;
		this.orderses = orderses;
		this.trips = trips;
		this.orderses_1 = orderses_1;
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

	@Column(name = "value", nullable = false, length = 50)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "keying", nullable = false, length = 50)
	public String getKeying() {
		return this.keying;
	}

	public void setKeying(String keying) {
		this.keying = keying;
	}

	@Column(name = "type", length = 50)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "descing")
	public String getDescing() {
		return this.descing;
	}

	public void setDescing(String descing) {
		this.descing = descing;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sequence")
	public Set<Orders> getOrderses() {
		return this.orderses;
	}

	public void setOrderses(Set<Orders> orderses) {
		this.orderses = orderses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sequence")
	public Set<Trip> getTrips() {
		return this.trips;
	}

	public void setTrips(Set<Trip> trips) {
		this.trips = trips;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sequence")
	public Set<Orders> getOrderses_1() {
		return this.orderses_1;
	}

	public void setOrderses_1(Set<Orders> orderses_1) {
		this.orderses_1 = orderses_1;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sequence")
	public Set<Trip> getTrips_1() {
		return this.trips_1;
	}

	public void setTrips_1(Set<Trip> trips_1) {
		this.trips_1 = trips_1;
	}

}