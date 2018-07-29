package com.webmonitor;

import java.io.IOException;

import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpIOExceptionHandler;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseInterceptor;
import com.google.api.client.http.HttpUnsuccessfulResponseHandler;
import com.webmonitor.validator.IResponseValidator;
import com.webmonitor.vo.ResultVo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultHandler implements HttpExecuteInterceptor, 
											HttpResponseInterceptor,
											HttpUnsuccessfulResponseHandler,
											HttpIOExceptionHandler{
	
	private ResultVo result = null;
	private IResponseValidator responseBodyValidator ;
	
	
	
	@Override
	public void intercept(HttpRequest request) throws IOException {
		this.result.setEpoch( this.getTimeInMs()) ;
	}

	@Override
	public void interceptResponse(HttpResponse response) throws IOException {
		this.setResponseTime();
		if (response == null) {
			return ;
		}
		if ( response.isSuccessStatusCode() )
		{
			this.result.setStatusCheck (this.responseBodyValidator
											.isResponseValid(response) ? "GREEN": "RED");
			
		}
	}

	@Override
	public boolean handleResponse(HttpRequest request, HttpResponse errResponse, boolean retrySupported) throws IOException {
		this.setResponseTime();
		return true;
	}

	@Override
	public boolean handleIOException(HttpRequest request, boolean retrySupported) throws IOException {
		this.setResponseTime();
		this.result.setStatusCheck("NET") ;
		return true;
	}
	
	protected void setResponseTime() {
		this.result.setResponseTimeInms(this.getTimeInMs() - this.result.getEpoch());
	}

	public ResultHandler(IResponseValidator responseBodyValidator) {
		super();
		this.responseBodyValidator = responseBodyValidator;
	}
	
	public long getTimeInMs() {
		return System.currentTimeMillis() ;
	}

}
