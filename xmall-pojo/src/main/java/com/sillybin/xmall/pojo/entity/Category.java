package com.sillybin.xmall.pojo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="sys_category")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long categoryId;
	private Category parent;
	private String text;
	private Status status;
	private Integer sortOrder;
	private List<Category> categoryList;
	private User createUser;
	private Date createTime;
	private User updateUser;
	private Date updateTime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	@ManyToOne(targetEntity=Category.class, fetch=FetchType.EAGER,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="parent_id")
	public Category getParent() {
		return parent;
	}
	public void setParent(Category parent) {
		this.parent = parent;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@ManyToOne(targetEntity=Status.class, fetch=FetchType.EAGER,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="status_id")
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	@OneToMany(targetEntity=Category.class,
			mappedBy="parent", fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	public List<Category> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
	
	@ManyToOne(targetEntity=User.class, fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="create_user")
	public User getCreateUser() {
		return createUser;
	}
	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@ManyToOne(targetEntity=User.class, fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="update_user")
	public User getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
