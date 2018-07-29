package com.webmonitor;

import java.io.IOException;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestConfiguration implements HttpRequestInitializer {

	private int connectionTimeOut = 2000;
	private int readTimeOut = 2000 ;
	private int numberOfRetries = 3 ;
	private ResultHandler handler ;
	
	
	@Override
	public void initialize(HttpRequest request) throws IOException {
		request.setConnectTimeout(this.connectionTimeOut) 
						.setReadTimeout(this.readTimeOut) 
						.setNumberOfRetries(this.numberOfRetries) 
						.setInterceptor(this.handler) 
						.setFollowRedirects(true)
						.setResponseInterceptor(this.handler) 
						.setUnsuccessfulResponseHandler(this.handler) 
						.setIOExceptionHandler(this.handler);
	}

}
