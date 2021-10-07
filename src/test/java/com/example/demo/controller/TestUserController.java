package com.example.demo.controller;


import static org.hamcrest.CoreMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.User;
import com.example.demo.repo.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class TestUserController {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserRepository urepo;

	private final ObjectMapper om=new ObjectMapper();
	
	@Test
	public void testSaveUserShouldReturnCreated() throws JsonProcessingException, Exception
	{
		User user=new User(1,"Prudhvi","prudhvi","prudhvi@gmail.com","Goa");
		when(urepo.save(any(User.class))).thenReturn(user);
		mockMvc.perform(post("/user/save")				
				.content(om.writeValueAsString(user))
				.header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.uid",is(1)))
				.andExpect(jsonPath("$.username",is("Prudhvi")))
				.andExpect(jsonPath("$.password",is("prudhvi")))
				.andExpect(jsonPath("$.email",is("prudhvi@gmail.com")))
				.andExpect(jsonPath("$.city",is("Goa")))
				;
			verify(urepo,times(1)).save(any(User.class));
		}
	
	
	@Test
	public void testAllUsers() throws Exception
	{
		List allUsers=Arrays.asList(new User(1,"Prudhvi","prudhvi","prudhvi@gmail.com","Goa"),
					  new User(2,"Praveen","praveen","praveen@gmail.com","Mumbai"));
		when(urepo.findAll()).thenReturn(allUsers);
		mockMvc.perform(get("/user/allUsers"))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$",hasSize(2)))
						.andExpect(jsonPath("$[0].uid",is(1)))
						.andExpect(jsonPath("$[0].username",is("Prudhvi")))
						.andExpect(jsonPath("$[0].password",is("prudhvi")))
						.andExpect(jsonPath("$[0].email",is("prudhvi@gmail.com")))
						.andExpect(jsonPath("$[0].city",is("Goa")))
						.andExpect(jsonPath("$[1].uid",is(2)))
						.andExpect(jsonPath("$[1].username",is("Praveen")))
						.andExpect(jsonPath("$[1].password",is("praveen")))
						.andExpect(jsonPath("$[1].email",is("praveen@gmail.com")))
						.andExpect(jsonPath("$[1].city",is("Mumbai")))
						;
		
		verify(urepo,times(1)).findAll();
		
	}
	
}
