package com.scb.pod2.login.exception;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.WebRequest;

@RunWith(SpringRunner.class)
public class UserLoginExceptionHandlerTest {
	
	private UserLoginExceptionHandler uleh = new UserLoginExceptionHandler();
	
	@Mock
	private WebRequest webRequest;

	@Test
	public void testHandleAllException() {
		ResponseEntity re = uleh.handleAllException(Mockito.mock(Exception.class), webRequest);
		
		Assert.assertNotNull(re);
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, re.getStatusCode());
	}
	
	
	@Test
	public void TestLoginFailedException() {
		ResponseEntity re = uleh.loginFailedException(Mockito.mock(Exception.class), webRequest);
		
		Assert.assertNotNull(re);
		Assert.assertEquals(HttpStatus.UNAUTHORIZED, re.getStatusCode());
	}
	
	@Test
	public void TestFallbackException() {
		ResponseEntity re = uleh.fallbackException(Mockito.mock(Exception.class), webRequest);
		
		FallbackException fb = new FallbackException("Testing");
		
		Assert.assertNotNull(fb);
		Assert.assertNotNull(re);
		Assert.assertEquals(HttpStatus.SERVICE_UNAVAILABLE, re.getStatusCode());
	}
}
