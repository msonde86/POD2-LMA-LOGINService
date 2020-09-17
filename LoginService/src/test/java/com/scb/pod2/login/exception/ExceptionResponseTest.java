package com.scb.pod2.login.exception;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ExceptionResponseTest {
	@Test
	public void testExceptionResponse() {
		ExceptionResponse exceptionResp = new ExceptionResponse(new Date(), "Test Exception", "error exception response");
		
		Assert.assertNotNull(exceptionResp.getTimeStamp());
		Assert.assertEquals("Test Exception", exceptionResp.getMessage());
		Assert.assertEquals("error exception response", exceptionResp.getDescription());
	}
}
