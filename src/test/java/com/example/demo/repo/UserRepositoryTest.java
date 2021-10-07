package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.demo.entity.User;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
public class UserRepositoryTest {

	@Autowired
	private UserRepository urepo;
	
	@Test
	@Rollback(value = false)
	@Order(1)
	public void testSaveUser()
	{	
		User user=new User("Prudhvi","prudhvi","prudhvi@gmail.com","Goa");
		User savedUser=urepo.save(user);
		Assertions.assertNotNull(savedUser);
		Assertions.assertEquals("Prudhvi",savedUser.getUsername());
	}
	
	@Test
	@Order(2)
	public void testGetAllUsers()
	{
		List<User> allUsers=urepo.findAll();
		System.out.println(allUsers);
		Assertions.assertEquals(1,allUsers.size());
	}
	
	@Test
	@Order(3)
	public void testGetUserById()
	{
		Integer eid=1;
		Optional<User> user=urepo.findById(eid);
		boolean isUserAvialable=user.isPresent();
		System.out.println("User With ID :"+eid+user.get());
		Assertions.assertTrue(isUserAvialable);
	}
	
}
