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
 * Theme entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "theme", catalog = "qunawan")
public class Theme implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Set<Themeontrip> themeontrips = new HashSet<Themeontrip>(0);

	// Constructors

	/** default constructor */
	public Theme() {
	}

	/** minimal constructor */
	public Theme(String name) {
		this.name = name;
	}

	/** full constructor */
	public Theme(String name, Set<Themeontrip> themeontrips) {
		this.name = name;
		this.themeontrips = themeontrips;
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

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "theme")
	public Set<Themeontrip> getThemeontrips() {
		return this.themeontrips;
	}

	public void setThemeontrips(Set<Themeontrip> themeontrips) {
		this.themeontrips = themeontrips;
	}

}