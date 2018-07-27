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
		request.setConnectTimeout(this.connectionTimeOut) ;
		request.setReadTimeout(this.readTimeOut) ;
		request.setNumberOfRetries(this.numberOfRetries) ;
		request.setInterceptor(this.handler) ;
		request.setResponseInterceptor(this.handler) ;
		request.setUnsuccessfulResponseHandler(this.handler) ;
	}

}
