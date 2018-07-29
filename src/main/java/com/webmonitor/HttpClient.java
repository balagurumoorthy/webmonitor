package com.webmonitor;

import java.io.IOException;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.webmonitor.validator.ResponseBodyValidator;
import com.webmonitor.vo.ResultVo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HttpClient {

	private  HttpTransport httpTransport ;

	public HttpClient() {
		this.setHttpTransport(new ApacheHttpTransport());
	}
	
	 
	
	public HttpClient(HttpTransport httpTransport) {
		this.setHttpTransport(httpTransport) ;
	}

	public ResultVo checkUrl(String url) {

		RequestConfiguration initializer = new RequestConfiguration();
		ResultHandler resultHandler = new ResultHandler(new ResponseBodyValidator());
		initializer.setHandler(resultHandler);
		ResultVo result = new ResultVo(url);

		resultHandler.setResult(result);
		
		HttpRequestFactory requestFactory = httpTransport.createRequestFactory(initializer);
		try {
			requestFactory.buildGetRequest(new GenericUrl(url)).execute();
		} catch (IOException ioException) {
			result.setStatusCheck("NET");
		}

		return resultHandler.getResult();

	}
	
	

}
