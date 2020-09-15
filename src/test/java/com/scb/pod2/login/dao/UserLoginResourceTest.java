package com.scb.pod2.login.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.scb.pod2.login.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class UserLoginResourceTest {
	
	@Autowired
	UserLoginResource loginResource;
	
	private User user;
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new User();
		user.setUserId(101);
		user.setEmailId("vinothencode@gmail.com");
		user.setPassword("cGFzc3dvcmQ=");
	}
	@Test
	public void findUserTest() {
		User userData=loginResource.findUser(user.getEmailId(), user.getPassword());
		assertNotNull(userData);
		assertEquals(user.getEmailId(), userData.getEmailId());
		assertEquals(user.getPassword(), userData.getPassword());
	}
}
