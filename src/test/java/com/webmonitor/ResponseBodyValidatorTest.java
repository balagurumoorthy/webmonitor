package com.webmonitor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Test;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.webmonitor.vo.ResultVo;

import junit.framework.Assert;

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
						// TODO Auto-generated method stub
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
		 assertSame(resultVo.getStatusCheck() , "GREEN") ;
		 
	}
}
