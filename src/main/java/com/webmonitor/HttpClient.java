package com.webmonitor;

import java.io.IOException;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.webmonitor.validator.ResponseBodyValidator;
import com.webmonitor.vo.ResultVo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HttpClient {

	private  HttpTransport HTTP_TRANSPORT ;

	public HttpClient() {
		this.setHTTP_TRANSPORT(new NetHttpTransport());
	}
	
	 
	
	public HttpClient(HttpTransport httpTransport) {
		this.setHTTP_TRANSPORT(httpTransport) ;
	}

	public ResultVo checkUrl(String url) {

		RequestConfiguration initializer = new RequestConfiguration();
		ResultHandler resultHandler = new ResultHandler(new ResponseBodyValidator());
		initializer.setHandler(resultHandler);
		ResultVo result = new ResultVo(url);

		resultHandler.setResult(result);
		
		HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(initializer);
		try {
			requestFactory.buildGetRequest(new GenericUrl(url)).execute();
		} catch (IOException ioException) {
			result.setStatusCheck("NET");
		}

		return resultHandler.getResult();

	}
	
	

}
