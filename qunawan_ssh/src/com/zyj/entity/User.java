package com.zyj.entity;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "qunawan", uniqueConstraints = @UniqueConstraint(columnNames = {
		"phone", "name", "email" }))
public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private City city;
	private String phone;
	private String password;
	private String name;
	private Boolean sex;
	private String imgPath;
	private String email;
	private String realName;
	private Date birthday;
	private Set<Orders> orderses = new HashSet<Orders>(0);
	private Set<Contact> contacts = new HashSet<Contact>(0);
	private Set<Comment> comments = new HashSet<Comment>(0);
	private Set<Orders> orderses_1 = new HashSet<Orders>(0);
	private Set<Contact> contacts_1 = new HashSet<Contact>(0);
	private Set<Comment> comments_1 = new HashSet<Comment>(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String password) {
		this.password = password;
	}

	/** full constructor */
	public User(City city, String phone, String password, String name,
			Boolean sex, String imgPath, String email, String realName,
			Date birthday, Set<Orders> orderses, Set<Contact> contacts,
			Set<Comment> comments, Set<Orders> orderses_1,
			Set<Contact> contacts_1, Set<Comment> comments_1) {
		this.city = city;
		this.phone = phone;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.imgPath = imgPath;
		this.email = email;
		this.realName = realName;
		this.birthday = birthday;
		this.orderses = orderses;
		this.contacts = contacts;
		this.comments = comments;
		this.orderses_1 = orderses_1;
		this.contacts_1 = contacts_1;
		this.comments_1 = comments_1;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "city")
	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Column(name = "phone", length = 100)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "password", nullable = false, length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "name", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "sex")
	public Boolean getSex() {
		return this.sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	@Column(name = "img_path", length = 100)
	public String getImgPath() {
		return this.imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "real_name", length = 20)
	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "birthday", length = 10)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Orders> getOrderses() {
		return this.orderses;
	}

	public void setOrderses(Set<Orders> orderses) {
		this.orderses = orderses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Contact> getContacts() {
		return this.contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Orders> getOrderses_1() {
		return this.orderses_1;
	}

	public void setOrderses_1(Set<Orders> orderses_1) {
		this.orderses_1 = orderses_1;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Contact> getContacts_1() {
		return this.contacts_1;
	}

	public void setContacts_1(Set<Contact> contacts_1) {
		this.contacts_1 = contacts_1;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Comment> getComments_1() {
		return this.comments_1;
	}

	public void setComments_1(Set<Comment> comments_1) {
		this.comments_1 = comments_1;
	}

}