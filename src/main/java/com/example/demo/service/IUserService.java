package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.User;

public interface IUserService {

	public User saveUser(User user);
	public List<User> getAllUsers();
	public void deleteUser(Integer eid);
	public Optional<User> getUserById(Integer uid);
	public User updateUser(User user);
	public List<User> getAllUsersByCity(String city);
	public Optional<User> getUserByEmail(String email);
	
}
