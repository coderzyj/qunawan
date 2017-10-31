package com.zyj.entity;

import java.sql.Timestamp;
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
 * Comment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "comment", catalog = "qunawan")
public class Comment implements java.io.Serializable {

	// Fields

	private Integer id;
	private Trip trip;
	private User user;
	private Orders orders;
	private Integer place;
	private Integer hotel;
	private Integer service;
	private Integer traffic;
	private String content;
	private Timestamp time;
	private Integer usefull;
	private Integer useless;
	private Set<Commentpicture> commentpictures = new HashSet<Commentpicture>(0);

	// Constructors

	/** default constructor */
	public Comment() {
	}

	/** minimal constructor */
	public Comment(User user, Orders orders) {
		this.user = user;
		this.orders = orders;
	}

	/** full constructor */
	public Comment(Trip trip, User user, Orders orders, Integer place,
			Integer hotel, Integer service, Integer traffic, String content,
			Timestamp time, Integer usefull, Integer useless,
			Set<Commentpicture> commentpictures) {
		this.trip = trip;
		this.user = user;
		this.orders = orders;
		this.place = place;
		this.hotel = hotel;
		this.service = service;
		this.traffic = traffic;
		this.content = content;
		this.time = time;
		this.usefull = usefull;
		this.useless = useless;
		this.commentpictures = commentpictures;
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
	@JoinColumn(name = "trip")
	public Trip getTrip() {
		return this.trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orders", nullable = false)
	public Orders getOrders() {
		return this.orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	@Column(name = "place")
	public Integer getPlace() {
		return this.place;
	}

	public void setPlace(Integer place) {
		this.place = place;
	}

	@Column(name = "hotel")
	public Integer getHotel() {
		return this.hotel;
	}

	public void setHotel(Integer hotel) {
		this.hotel = hotel;
	}

	@Column(name = "service")
	public Integer getService() {
		return this.service;
	}

	public void setService(Integer service) {
		this.service = service;
	}

	@Column(name = "traffic")
	public Integer getTraffic() {
		return this.traffic;
	}

	public void setTraffic(Integer traffic) {
		this.traffic = traffic;
	}

	@Column(name = "content", length = 200)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "time", length = 19)
	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	@Column(name = "usefull")
	public Integer getUsefull() {
		return this.usefull;
	}

	public void setUsefull(Integer usefull) {
		this.usefull = usefull;
	}

	@Column(name = "useless")
	public Integer getUseless() {
		return this.useless;
	}

	public void setUseless(Integer useless) {
		this.useless = useless;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "comment")
	public Set<Commentpicture> getCommentpictures() {
		return this.commentpictures;
	}

	public void setCommentpictures(Set<Commentpicture> commentpictures) {
		this.commentpictures = commentpictures;
	}

}