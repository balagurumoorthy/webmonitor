package com.webmonitor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.io.IOException;

import org.junit.Test;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.webmonitor.validator.ResponseBodyValidator;
import com.webmonitor.vo.ResultVo;

public class ResponseBodyValidatorTest {

	@Test
	public void responseSearchString() 
	{
		 HttpTransport transport = new MockHttpTransport() {

			@Override
			public LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
				return new MockLowLevelHttpRequest() {

					@Override
					public LowLevelHttpResponse execute() throws IOException {
						MockLowLevelHttpResponse response =  new MockLowLevelHttpResponse() ;
						response.setStatusCode(200) ;
						response.setContent("Completed: GREEN : Changes for testing") ;
						return response ;
					}
					
				} ;
			}	
			 	
		 } ;
		 
		 String url = "http://testurl.com" ;
		 HttpClient client = new HttpClient(transport) ;
		 ResultVo resultVo = client.checkUrl(url);
		 assertNotNull(resultVo);
		 assertSame(url, resultVo.getCheckUrl()) ;
		 assertNotNull(resultVo.getStatusCheck()) ;
		 assertSame("GREEN",resultVo.getStatusCheck()  ) ;
		 
	}
	
	@Test
	public void responseisEmptyString() 
	{
		 HttpTransport transport = new MockHttpTransport() {

			@Override
			public LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
				return new MockLowLevelHttpRequest() {

					@Override
					public LowLevelHttpResponse execute() throws IOException {
						MockLowLevelHttpResponse response =  new MockLowLevelHttpResponse() ;
						response.setStatusCode(200) ;
						response.setContent((String)null) ;
						return response ;
					}
					
				} ;
			}	
			 	
		 } ;
		 
		 String url = "http://testurl.com" ;
		 HttpClient client = new HttpClient(transport) ;
		 ResultVo resultVo = client.checkUrl(url);
		 assertNotNull(resultVo);
		 assertSame(url, resultVo.getCheckUrl()) ;
		 assertNotNull(resultVo.getStatusCheck()) ;
		 assertSame( "RED",resultVo.getStatusCheck()) ;
		 
	}
	
	
	@Test
	public void checkTimeOutsAreSetOnRequest() 
	{
		 String url = "http://testurl.com" ;
		 HttpClient client = new HttpClient() ;
		
		 	RequestConfiguration initializer = new RequestConfiguration();
			ResultHandler resultHandler = new ResultHandler(new ResponseBodyValidator());
			initializer.setHandler(resultHandler);
			ResultVo result = new ResultVo(url);

			resultHandler.setResult(result);
			
			HttpRequestFactory requestFactory = client.getHttpTransport().createRequestFactory(initializer);
			HttpRequest httpRequest = null ;
			IOException expValidator = null ;
			
			try {
				httpRequest = requestFactory.buildGetRequest(new GenericUrl(url)) ;
			}catch(IOException exp) {
				expValidator = exp ;
				
			}
		 assertNull(expValidator) ;	
		 assertNull(result.getStatusCheck()) ;
		 assertSame(httpRequest.getUnsuccessfulResponseHandler(), initializer.getHandler()) ;
		 assertSame(httpRequest.getIOExceptionHandler(), initializer.getHandler()) ;
		 assertSame(httpRequest.getResponseInterceptor(),initializer.getHandler()) ;
		 assertSame(httpRequest.getNumberOfRetries(), initializer.getNumberOfRetries());
		 assertEquals("Is Timeout setting same ?",httpRequest.getConnectTimeout(),initializer.getConnectionTimeOut()) ;
		
		 
	}
	
}
