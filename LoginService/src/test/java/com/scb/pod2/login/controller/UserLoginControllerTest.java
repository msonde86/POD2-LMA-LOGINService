package com.scb.pod2.login.controller;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.scb.pod2.login.service.UserLoginService;


@RunWith(SpringRunner.class)
@WebMvcTest(UserLoginControllerTest.class)
public class UserLoginControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private UserLoginService userLoginService;
	
	
}
