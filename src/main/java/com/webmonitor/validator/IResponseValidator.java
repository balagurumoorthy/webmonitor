package com.webmonitor.validator;

import java.io.IOException;

import com.google.api.client.http.HttpResponse;

public interface IResponseValidator {

	public boolean isResponseValid(HttpResponse response) throws IOException;
}
