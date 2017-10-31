package com.zyj.entity;

import java.util.HashSet;
import java.util.Iterator;
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
 * Trip entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "trip", catalog = "qunawan")
public class Trip implements java.io.Serializable {

	// Fields

	private Integer id;
	private City city;
	private Sequence sequence;
	private Position position;
	private Integer num;
	private String title;
	private String STitle;
	private String traffic;
	private String hotel;
	private Integer time;
	private Float goodRate;
	private Float placeScore;
	private Float hotelScore;
	private Float serviceScore;
	private Float trafficScore;
	private Boolean isOk;
	private String main_picname;
	private float min_price;
	private Set<Orders> orderses = new HashSet<Orders>(0);
	private Set<Trippicture> trippictures = new HashSet<Trippicture>(0);
	private Set<Schedule> schedules = new HashSet<Schedule>(0);
	private Set<Orders> orderses_1 = new HashSet<Orders>(0);
	private Set<Detail> details = new HashSet<Detail>(0);
	private Set<Comment> comments = new HashSet<Comment>(0);
	private Set<Themeontrip> themeontrips = new HashSet<Themeontrip>(0);
	private Set<Price> prices = new HashSet<Price>(0);
	private Set<Placeontrip> placeontrips = new HashSet<Placeontrip>(0);
	private Set<Comment> comments_1 = new HashSet<Comment>(0);

	// Constructors

	/** default constructor */
	public Trip() {
	}

	/** minimal constructor */
	public Trip(City city, Sequence sequence, Position position, Integer num,
			String title, String STitle) {
		this.city = city;
		this.sequence = sequence;
		this.position = position;
		this.num = num;
		this.title = title;
		this.STitle = STitle;
	}

	/** full constructor */
	public Trip(City city, Sequence sequence, Position position, Integer num,
			String title, String STitle, String traffic, String hotel,
			Integer time, Float goodRate, Float placeScore, Float hotelScore,
			Float serviceScore, Float trafficScore, Boolean isOk,
			Set<Orders> orderses, Set<Trippicture> trippictures,
			Set<Schedule> schedules, Set<Orders> orderses_1,
			Set<Detail> details, Set<Comment> comments,
			Set<Themeontrip> themeontrips, Set<Price> prices,
			Set<Placeontrip> placeontrips, Set<Comment> comments_1) {
		this.city = city;
		this.sequence = sequence;
		this.position = position;
		this.num = num;
		this.title = title;
		this.STitle = STitle;
		this.traffic = traffic;
		this.hotel = hotel;
		this.time = time;
		this.goodRate = goodRate;
		this.placeScore = placeScore;
		this.hotelScore = hotelScore;
		this.serviceScore = serviceScore;
		this.trafficScore = trafficScore;
		this.isOk = isOk;
		this.orderses = orderses;
		this.trippictures = trippictures;
		this.schedules = schedules;
		this.orderses_1 = orderses_1;
		this.details = details;
		this.comments = comments;
		this.themeontrips = themeontrips;
		this.prices = prices;
		this.placeontrips = placeontrips;
		this.comments_1 = comments_1;
	}
	
	public float getMin_price() {
		return min_price;
	}

	public void setMin_price(float min_price) {
		this.min_price = min_price;
	}

	public void setMain_picname(String main_picname) {
		this.main_picname = main_picname;
	}

	public String getMain_picname() {
		return main_picname;
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
	@JoinColumn(name = "start_place", nullable = false)
	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type", nullable = false)
	public Sequence getSequence() {
		return this.sequence;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "position", nullable = false)
	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Column(name = "num", nullable = false)
	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Column(name = "title", nullable = false, length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "s_title", nullable = false, length = 10)
	public String getSTitle() {
		return this.STitle;
	}

	public void setSTitle(String STitle) {
		this.STitle = STitle;
	}

	@Column(name = "traffic", length = 20)
	public String getTraffic() {
		return this.traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}

	@Column(name = "hotel", length = 20)
	public String getHotel() {
		return this.hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	@Column(name = "time")
	public Integer getTime() {
		return this.time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	@Column(name = "good_rate", precision = 5, scale = 1)
	public Float getGoodRate() {
		return this.goodRate;
	}

	public void setGoodRate(Float goodRate) {
		this.goodRate = goodRate;
	}

	@Column(name = "place_score", precision = 5, scale = 1)
	public Float getPlaceScore() {
		return this.placeScore;
	}

	public void setPlaceScore(Float placeScore) {
		this.placeScore = placeScore;
	}

	@Column(name = "hotel_score", precision = 5, scale = 1)
	public Float getHotelScore() {
		return this.hotelScore;
	}

	public void setHotelScore(Float hotelScore) {
		this.hotelScore = hotelScore;
	}

	@Column(name = "service_score", precision = 5, scale = 1)
	public Float getServiceScore() {
		return this.serviceScore;
	}

	public void setServiceScore(Float serviceScore) {
		this.serviceScore = serviceScore;
	}

	@Column(name = "traffic_score", precision = 5, scale = 1)
	public Float getTrafficScore() {
		return this.trafficScore;
	}

	public void setTrafficScore(Float trafficScore) {
		this.trafficScore = trafficScore;
	}

	@Column(name = "is_ok")
	public Boolean getIsOk() {
		return this.isOk;
	}

	public void setIsOk(Boolean isOk) {
		this.isOk = isOk;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "trip")
	public Set<Orders> getOrderses() {
		return this.orderses;
	}

	public void setOrderses(Set<Orders> orderses) {
		this.orderses = orderses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "trip")
	public Set<Trippicture> getTrippictures() {
		return this.trippictures;
	}

	public void setTrippictures(Set<Trippicture> trippictures) {
		this.trippictures = trippictures;
		for (Trippicture p : this.trippictures) {
			if (p.getType() == 0) {
				setMain_picname(p.getName());
			}
		}
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "trip")
	public Set<Schedule> getSchedules() {
		return this.schedules;
	}

	public void setSchedules(Set<Schedule> schedules) {
		this.schedules = schedules;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "trip")
	public Set<Orders> getOrderses_1() {
		return this.orderses_1;
	}

	public void setOrderses_1(Set<Orders> orderses_1) {
		this.orderses_1 = orderses_1;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "trip")
	public Set<Detail> getDetails() {
		return this.details;
	}

	public void setDetails(Set<Detail> details) {
		this.details = details;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "trip")
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "trip")
	public Set<Themeontrip> getThemeontrips() {
		return this.themeontrips;
	}

	public void setThemeontrips(Set<Themeontrip> themeontrips) {
		this.themeontrips = themeontrips;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "trip")
	public Set<Price> getPrices() {
		return this.prices;
	}

	public void setPrices(Set<Price> prices) {
		this.prices = prices;
		float min = 0f;
		/*Iterator<Price> i = this.prices.iterator();
		min = i.next().getPrice();
		while(i.hasNext()){
				if (i.next().getPrice() <= min) {
					min = i.next().getPrice();
				}
			}*/
		if(!prices.isEmpty()&prices!=null)
		{
			min=prices.iterator().next().getPrice();
			for(Price p:prices)
			{
				if(p.getPrice()<min)
					min=p.getPrice();
			}
		}
		
		
		setMin_price(min);
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "trip")
	public Set<Placeontrip> getPlaceontrips() {
		return this.placeontrips;
	}

	public void setPlaceontrips(Set<Placeontrip> placeontrips) {
		this.placeontrips = placeontrips;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "trip")
	public Set<Comment> getComments_1() {
		return this.comments_1;
	}

	public void setComments_1(Set<Comment> comments_1) {
		this.comments_1 = comments_1;
	}

}