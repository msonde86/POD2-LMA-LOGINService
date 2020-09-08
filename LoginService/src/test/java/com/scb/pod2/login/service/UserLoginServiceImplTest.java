package com.scb.pod2.login.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.scb.pod2.login.dao.UserLoginResource;
import com.scb.pod2.login.dto.UserDTO;
import com.scb.pod2.login.model.User;



@RunWith(SpringRunner.class)
public class UserLoginServiceImplTest {
	
	@InjectMocks
	private UserLoginServiceImpl loginService;
	@Mock
	private UserLoginResource loginResource;
	
	private UserDTO userDto;
	private Map<String,String> jwtToken;
	private User user;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		userDto = new UserDTO();
		userDto.setEmailId("vinothencode@gmail.com");
		userDto.setPassword("password");
		user = new User();
		user.setUserId(101);
		user.setEmailId("vinothencode@gmail.com");
		user.setPassword("cGFzc3dvcmQ=");
		jwtToken = new HashMap<>();
		jwtToken.put("token", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2aW5vdGhlbmNvZGVAZ21haWwuY29tIiwiaWF0IjoxNTk5NDUzOTUzfQ.4R_KsFmwjNksDxwtE75brjR94JnZqxQGHQ5zzJJsDtg");
	}
	@Test
	public void loginUserTest() {
		when(loginResource.findUser(Mockito.anyString(),Mockito.anyString())).thenReturn(user);
		User user=loginResource.findUser(userDto.getEmailId(), loginService.encodePassword(userDto.getPassword()));
		assertNotNull(user);
		assertEquals(userDto.getEmailId(), user.getEmailId());
		assertEquals(loginService.encodePassword(userDto.getPassword()), user.getPassword());
	}
	@Test
	public void generateJwtTokenTest() {
		Map<String, String> token = loginService.generateJwtToken(user);
		assertNotNull(token);
		assertNotNull(token.get("token"));
	}
	@Test
	public void encodePasswordTest() {
		String encodedPassword=loginService.encodePassword(userDto.getPassword());
		assertNotNull(encodedPassword);
		assertEquals(user.getPassword(), encodedPassword);
	}
}
