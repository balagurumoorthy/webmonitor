package com.webmonitor.validator;

import java.io.IOException;

import com.google.api.client.http.HttpResponse;

import lombok.Getter;
@Getter
public class ResponseBodyValidator implements IResponseValidator {

	private String searchString = "GREEN" ;
	@Override
	public boolean isResponseValid(HttpResponse response) throws IOException {
		
		String content = response.parseAsString() ; //heavy operation, instead regex on charstream.
		
		return content != null && content.contains(searchString);
			
	}

}
