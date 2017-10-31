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
 * Commentpicture entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "commentpicture", catalog = "qunawan")
public class Commentpicture implements java.io.Serializable {

	// Fields

	private Integer id;
	private Comment comment;
	private byte[] data;
	private String name;

	// Constructors

	/** default constructor */
	public Commentpicture() {
	}

	/** full constructor */
	public Commentpicture(Comment comment, byte[] data, String name) {
		this.comment = comment;
		this.data = data;
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
	@JoinColumn(name = "comment", nullable = false)
	public Comment getComment() {
		return this.comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	@Column(name = "data", nullable = false)
	public byte[] getData() {
		return this.data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}