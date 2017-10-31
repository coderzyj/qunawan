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
 * Place entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "place", catalog = "qunawan")
public class Place implements java.io.Serializable {

	// Fields

	private Integer id;
	private City city;
	private String name;
	private Set<Placeontrip> placeontrips = new HashSet<Placeontrip>(0);

	// Constructors

	/** default constructor */
	public Place() {
	}

	/** minimal constructor */
	public Place(String name) {
		this.name = name;
	}

	/** full constructor */
	public Place(City city, String name, Set<Placeontrip> placeontrips) {
		this.city = city;
		this.name = name;
		this.placeontrips = placeontrips;
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
	@JoinColumn(name = "city")
	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "place")
	public Set<Placeontrip> getPlaceontrips() {
		return this.placeontrips;
	}

	public void setPlaceontrips(Set<Placeontrip> placeontrips) {
		this.placeontrips = placeontrips;
	}

}