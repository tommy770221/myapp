package com.tommy.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonInclude(Include.NON_NULL)
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID")
	private String id;
	@Column(name = "USERNAME")
	private String username;
    @JsonIgnore
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "email")
	private String email;
	@Column(name = "sex")
	private String sex;
	@Column(name = "userId")
	private String userId;
	@Column(name = "address")
	private String address;
	@Column(name = "post_no")
	private Integer post_no;
	@Column(name = "age")
	private Integer age;
	@Column(name = "enabled")
	private Boolean enabled;
	@Column(name = "birthday")
	private Date birthday;
	@Column(name = "createDate")
	private Date createDate;
	@Column(name = "updateDate")
	private Date updateDate;
	
	@JsonIgnore
	@ManyToMany(targetEntity = Role.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "userId"), }, inverseJoinColumns = { @JoinColumn(name = "roleId") })
	private List<Role> roles;

	public User() {
	}

	public User(String id, String username, String password, String email,
			String sex, String userId, String address, Integer post_no,
			Integer age, Boolean enabled, List<Role> roles, Date birthday,
			Date createDate, Date updateDate) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.sex = sex;
		this.userId = userId;
		this.address = address;
		this.post_no = post_no;
		this.age = age;
		this.enabled = enabled;
		this.roles = roles;
		this.birthday = birthday;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPost_no() {
		return post_no;
	}

	public void setPost_no(Integer post_no) {
		this.post_no = post_no;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}

	public void removeRole(Role role) {
		this.roles.remove(role);
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {

		try {
			this.birthday = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
		} catch (ParseException e) {
			e.printStackTrace();
			this.birthday=null;
		}

	}

	public String birthDateToString(){
		if(this.birthday!=null){
		 SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		 String birthString=sdFormat.format(this.birthday);
		return birthString;
		}else{
			return null;
		}
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@PrePersist
	void preInsert() {
		this.createDate = new Date();
		this.enabled=false;
	}

	@PreUpdate
	void preUpdate() {
		updateDate = new Date();
	}
}
