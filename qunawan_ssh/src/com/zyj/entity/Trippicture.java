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
 * Trippicture entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "trippicture", catalog = "qunawan")
public class Trippicture implements java.io.Serializable {

	// Fields

	private Integer id;
	private Trip trip;
	private byte[] data;
	private Integer type;
	private String name;

	// Constructors

	/** default constructor */
	public Trippicture() {
	}

	/** minimal constructor */
	public Trippicture(byte[] data) {
		this.data = data;
	}

	/** full constructor */
	public Trippicture(Trip trip, byte[] data, Integer type, String name) {
		this.trip = trip;
		this.data = data;
		this.type = type;
		this.name = name;
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

	@Column(name = "data", nullable = false)
	public byte[] getData() {
		return this.data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}