package com.yashashree.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="proj2_blog")
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String BlogId;

	@NotEmpty(message = "Please Enter Title")
	private String title;

	@NotEmpty(message = "Please Enter Description")
	private String description;

	private int id;
	// it is for userID 
	
	private String datetime;
	
	private char status;

	public String getBlogId() {
		return BlogId;
	}

	public void setBlogId(String blogId) {
		BlogId = blogId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
}