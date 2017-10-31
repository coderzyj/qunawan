package com.zyj.entity;

import java.sql.Timestamp;
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
 * Orders entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "orders", catalog = "qunawan")
public class Orders implements java.io.Serializable {

	// Fields

	private Integer id;
	private Trip trip;
	private User user;
	private Sequence sequence;
	private String orderNo;
	private Integer num;
	private Date startTime;
	private String goPoint;
	private String goTime;
	private Float totalPrice;
	private Timestamp createTime;
	private Set<Ordercontact> ordercontacts = new HashSet<Ordercontact>(0);
	private Set<Comment> comments = new HashSet<Comment>(0);
	private Set<Comment> comments_1 = new HashSet<Comment>(0);

	// Constructors

	/** default constructor */
	public Orders() {
	}

	/** minimal constructor */
	public Orders(Trip trip, User user, String orderNo) {
		this.trip = trip;
		this.user = user;
		this.orderNo = orderNo;
	}

	/** full constructor */
	public Orders(Trip trip, User user, Sequence sequence, String orderNo,
			Integer num, Date startTime, String goPoint, String goTime,
			Float totalPrice, Timestamp createTime,
			Set<Ordercontact> ordercontacts, Set<Comment> comments,
			Set<Comment> comments_1) {
		this.trip = trip;
		this.user = user;
		this.sequence = sequence;
		this.orderNo = orderNo;
		this.num = num;
		this.startTime = startTime;
		this.goPoint = goPoint;
		this.goTime = goTime;
		this.totalPrice = totalPrice;
		this.createTime = createTime;
		this.ordercontacts = ordercontacts;
		this.comments = comments;
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
	@JoinColumn(name = "trip", nullable = false)
	public Trip getTrip() {
		return this.trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "state")
	public Sequence getSequence() {
		return this.sequence;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	@Column(name = "orderNo", nullable = false, length = 20)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "num")
	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "start_time", length = 10)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column(name = "go_point", length = 50)
	public String getGoPoint() {
		return this.goPoint;
	}

	public void setGoPoint(String goPoint) {
		this.goPoint = goPoint;
	}

	@Column(name = "go_time", length = 50)
	public String getGoTime() {
		return this.goTime;
	}

	public void setGoTime(String goTime) {
		this.goTime = goTime;
	}

	@Column(name = "total_price", precision = 12, scale = 0)
	public Float getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name = "create_time", length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orders")
	public Set<Ordercontact> getOrdercontacts() {
		return this.ordercontacts;
	}

	public void setOrdercontacts(Set<Ordercontact> ordercontacts) {
		this.ordercontacts = ordercontacts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orders")
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orders")
	public Set<Comment> getComments_1() {
		return this.comments_1;
	}

	public void setComments_1(Set<Comment> comments_1) {
		this.comments_1 = comments_1;
	}

}