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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Schedule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "schedule", catalog = "qunawan")
public class Schedule implements java.io.Serializable {

	// Fields

	private Integer id;
	private Trip trip;
	private Traffic traffic;
	private Set<Dates> dateses = new HashSet<Dates>(0);

	// Constructors

	/** default constructor */
	public Schedule() {
	}

	/** minimal constructor */
	public Schedule(Trip trip, Traffic traffic) {
		this.trip = trip;
		this.traffic = traffic;
	}

	/** full constructor */
	public Schedule(Trip trip, Traffic traffic, Set<Dates> dateses) {
		this.trip = trip;
		this.traffic = traffic;
		this.dateses = dateses;
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
	@JoinColumn(name = "traffic", nullable = false)
	public Traffic getTraffic() {
		return this.traffic;
	}

	public void setTraffic(Traffic traffic) {
		this.traffic = traffic;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "schedule")
	public Set<Dates> getDateses() {
		return this.dateses;
	}

	public void setDateses(Set<Dates> dateses) {
		this.dateses = dateses;
	}

}