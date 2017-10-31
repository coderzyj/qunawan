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
 * City entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "city", catalog = "qunawan")
public class City implements java.io.Serializable {

	// Fields

	private Integer id;
	private City city;
	private String name;
	private Integer type;
	private Set<Place> places = new HashSet<Place>(0);
	private Set<User> users = new HashSet<User>(0);
	private Set<City> cities = new HashSet<City>(0);
	private Set<Trip> trips = new HashSet<Trip>(0);
	private Set<Place> places_1 = new HashSet<Place>(0);
	private Set<User> users_1 = new HashSet<User>(0);
	private Set<City> cities_1 = new HashSet<City>(0);
	private Set<Trip> trips_1 = new HashSet<Trip>(0);

	// Constructors

	/** default constructor */
	public City() {
	}

	/** minimal constructor */
	public City(String name, Integer type) {
		this.name = name;
		this.type = type;
	}

	/** full constructor */
	public City(City city, String name, Integer type, Set<Place> places,
			Set<User> users, Set<City> cities, Set<Trip> trips,
			Set<Place> places_1, Set<User> users_1, Set<City> cities_1,
			Set<Trip> trips_1) {
		this.city = city;
		this.name = name;
		this.type = type;
		this.places = places;
		this.users = users;
		this.cities = cities;
		this.trips = trips;
		this.places_1 = places_1;
		this.users_1 = users_1;
		this.cities_1 = cities_1;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Column(name = "name", nullable = false, length = 10)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "type", nullable = false)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "city")
	public Set<Place> getPlaces() {
		return this.places;
	}

	public void setPlaces(Set<Place> places) {
		this.places = places;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "city")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "city")
	public Set<City> getCities() {
		return this.cities;
	}

	public void setCities(Set<City> cities) {
		this.cities = cities;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "city")
	public Set<Trip> getTrips() {
		return this.trips;
	}

	public void setTrips(Set<Trip> trips) {
		this.trips = trips;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "city")
	public Set<Place> getPlaces_1() {
		return this.places_1;
	}

	public void setPlaces_1(Set<Place> places_1) {
		this.places_1 = places_1;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "city")
	public Set<User> getUsers_1() {
		return this.users_1;
	}

	public void setUsers_1(Set<User> users_1) {
		this.users_1 = users_1;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "city")
	public Set<City> getCities_1() {
		return this.cities_1;
	}

	public void setCities_1(Set<City> cities_1) {
		this.cities_1 = cities_1;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "city")
	public Set<Trip> getTrips_1() {
		return this.trips_1;
	}

	public void setTrips_1(Set<Trip> trips_1) {
		this.trips_1 = trips_1;
	}

}