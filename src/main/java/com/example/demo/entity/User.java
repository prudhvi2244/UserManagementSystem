package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users_tab")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	public User(String username,String password,String email,String city)
	{
		this.username=username;
		this.password=password;
		this.email=email;
		this.city=city;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uid;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String email;
	@Column
	private String city;

}
