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
 * Dates entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "dates", catalog = "qunawan")
public class Dates implements java.io.Serializable {

	// Fields

	private Integer id;
	private Schedule schedule;
	private String title;
	private Integer num;
	private String detail;
	private String hotel;
	private String meal;
	private String traffic;

	// Constructors

	/** default constructor */
	public Dates() {
	}

	/** minimal constructor */
	public Dates(Schedule schedule, String title, Integer num, String detail) {
		this.schedule = schedule;
		this.title = title;
		this.num = num;
		this.detail = detail;
	}

	/** full constructor */
	public Dates(Schedule schedule, String title, Integer num, String detail,
			String hotel, String meal, String traffic) {
		this.schedule = schedule;
		this.title = title;
		this.num = num;
		this.detail = detail;
		this.hotel = hotel;
		this.meal = meal;
		this.traffic = traffic;
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
	@JoinColumn(name = "schedule", nullable = false)
	public Schedule getSchedule() {
		return this.schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	@Column(name = "title", nullable = false, length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "num", nullable = false)
	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Column(name = "detail", nullable = false, length = 1000)
	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Column(name = "hotel", length = 50)
	public String getHotel() {
		return this.hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	@Column(name = "meal", length = 50)
	public String getMeal() {
		return this.meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}

	@Column(name = "traffic", length = 50)
	public String getTraffic() {
		return this.traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}

}