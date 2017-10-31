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

/**
 * Contact entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "contact", catalog = "qunawan")
public class Contact implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private String phone;
	private String name;
	private Boolean sex;
	private String email;
	private String cardno;
	private Date birthday;
	private Set<Ordercontact> ordercontacts = new HashSet<Ordercontact>(0);

	// Constructors

	/** default constructor */
	public Contact() {
	}

	/** minimal constructor */
	public Contact(User user, String phone, String name) {
		this.user = user;
		this.phone = phone;
		this.name = name;
	}

	/** full constructor */
	public Contact(User user, String phone, String name, Boolean sex,
			String email, String cardno, Date birthday,
			Set<Ordercontact> ordercontacts) {
		this.user = user;
		this.phone = phone;
		this.name = name;
		this.sex = sex;
		this.email = email;
		this.cardno = cardno;
		this.birthday = birthday;
		this.ordercontacts = ordercontacts;
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
	@JoinColumn(name = "user", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "phone", nullable = false, length = 100)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "name", nullable = false, length = 20)
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

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "cardno", length = 30)
	public String getCardno() {
		return this.cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "birthday", length = 10)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contact")
	public Set<Ordercontact> getOrdercontacts() {
		return this.ordercontacts;
	}

	public void setOrdercontacts(Set<Ordercontact> ordercontacts) {
		this.ordercontacts = ordercontacts;
	}

}