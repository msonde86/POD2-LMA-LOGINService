package com.scb.pod2.login.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.scb.pod2.login.model.User;
import com.scb.pod2.login.service.UserLoginService;


@RunWith(SpringRunner.class)
@WebMvcTest(value=UserLoginController.class)
public class UserLoginControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserLoginService userLoginService;
	private Map<String, String> jwtToken;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		jwtToken = new HashMap<>();
		jwtToken.put("token", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2aW5vdGhlbmNvZGVAZ21haWwuY29tIiwiaWF0IjoxNTk5NDUzOTUzfQ.4R_KsFmwjNksDxwtE75brjR94JnZqxQGHQ5zzJJsDtg");
	}
	
	@Test
	public void loginUserTest() throws Exception {
		String userJson = "{\"emailId\":\"vinothencode@gmail.com\",\"password\":\"password\"}";
		Mockito.when(userLoginService.checkUserLogin(Mockito.any(User.class))).thenReturn(jwtToken);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/login/authenticate").
				content(userJson).
				contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
	
}
