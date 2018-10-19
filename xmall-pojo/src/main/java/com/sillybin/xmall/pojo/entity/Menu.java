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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="sys_menu")
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long menuId;
	private Menu parent;
	private String text;
	private String url;
	private String icon;
	private Integer sortOrder;
	private Status status;
	private List<Menu> menuList;
	private List<Role> roleList;
	private User createUser;
	private Date createTime;
	private User updateUser;
	private Date updateTime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	
	@ManyToOne(targetEntity=Menu.class, fetch=FetchType.EAGER,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="parent_id")
	public Menu getParent() {
		return parent;
	}
	public void setParent(Menu parent) {
		this.parent = parent;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
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
	
	@OneToMany(targetEntity=Menu.class,
			mappedBy="parent", fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	public List<Menu> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
	
	@ManyToMany(targetEntity=Role.class, fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="sys_role_menu",
			joinColumns= {@JoinColumn(name="menu_id")},
			inverseJoinColumns= {@JoinColumn(name="role_id")})
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
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
