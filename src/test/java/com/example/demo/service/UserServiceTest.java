package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.entity.User;
import com.example.demo.repo.UserRepository;

@SpringBootTest
public class UserServiceTest {

	@MockBean
	private UserRepository urepo;

	@Autowired
	private IUserService uservice;

	@Test
	public void testSaveUserShouldReturnSavedUser() {
		User user = new User("Prudhvi", "prudhvi", "prudhvi@gmail.com", "Mumbai");
		when(urepo.save(user)).thenReturn(user);

		User savedUser = uservice.saveUser(user);
		assertThat(savedUser).isNotNull();
		Assertions.assertEquals(user.getUsername(), savedUser.getUsername());

	}
	
	@Test
	public void testDeleteUser()
	{
		Integer uid=1;
		uservice.deleteUser(uid);
		verify(urepo,times(1)).deleteById(uid);
	}
	
	@Test
	public void testGetUserByEmail()
	{
		String email="prudhvi@gmail.com";
		User user=new User("Prudhvi","prudhvi",email,"Goa");
		when(urepo.findByEmail(email)).thenReturn(Optional.of(user));
		Optional<User> userByEmail=uservice.getUserByEmail(email);
		System.out.println("User By Email :"+userByEmail.get());
		Assertions.assertTrue(userByEmail.isPresent());
		assertThat(userByEmail.get().getEmail()).isEqualTo(email);
	}
	

}
