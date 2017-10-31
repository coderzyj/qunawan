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
 * Traffic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "traffic", catalog = "qunawan")
public class Traffic implements java.io.Serializable {

	// Fields

	private Integer id;
	private String goPoint;
	private String goTime;
	private String returnPoint;
	private String returnTime;
	private Set<Schedule> schedules = new HashSet<Schedule>(0);

	// Constructors

	/** default constructor */
	public Traffic() {
	}

	/** minimal constructor */
	public Traffic(String goPoint, String goTime) {
		this.goPoint = goPoint;
		this.goTime = goTime;
	}

	/** full constructor */
	public Traffic(String goPoint, String goTime, String returnPoint,
			String returnTime, Set<Schedule> schedules) {
		this.goPoint = goPoint;
		this.goTime = goTime;
		this.returnPoint = returnPoint;
		this.returnTime = returnTime;
		this.schedules = schedules;
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

	@Column(name = "go_point", nullable = false, length = 50)
	public String getGoPoint() {
		return this.goPoint;
	}

	public void setGoPoint(String goPoint) {
		this.goPoint = goPoint;
	}

	@Column(name = "go_time", nullable = false, length = 50)
	public String getGoTime() {
		return this.goTime;
	}

	public void setGoTime(String goTime) {
		this.goTime = goTime;
	}

	@Column(name = "return_point", length = 50)
	public String getReturnPoint() {
		return this.returnPoint;
	}

	public void setReturnPoint(String returnPoint) {
		this.returnPoint = returnPoint;
	}

	@Column(name = "return_time", length = 50)
	public String getReturnTime() {
		return this.returnTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "traffic")
	public Set<Schedule> getSchedules() {
		return this.schedules;
	}

	public void setSchedules(Set<Schedule> schedules) {
		this.schedules = schedules;
	}

}