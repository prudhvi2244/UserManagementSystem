package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repo.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User saveUser(User user) {
			return userRepo.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public void deleteUser(Integer eid) {
		userRepo.deleteById(eid);

	}

	@Override
	public Optional<User> getUserById(Integer uid) {
			return userRepo.findById(uid);
	}

	@Override
	public User updateUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public List<User> getAllUsersByCity(String city) {
		return userRepo.findByCity(city);
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
			return userRepo.findByEmail(email);
	}

}
