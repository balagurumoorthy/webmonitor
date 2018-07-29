package com.webmonitor;


import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import com.webmonitor.vo.ResultVo;


public class ResponseValidatorTest {

	@Rule
	public  WireMockClassRule wireMockRule = new WireMockClassRule(wireMockConfig().dynamicPort()); // No-args constructor defaults to port 8080

	@Test
	public void successResponse() {
		int port = wireMockRule.port();
	    stubFor(get(urlEqualTo("/monitor/status"))
	            .willReturn(aResponse()
	                .withStatus(200)
	                .withHeader("Content-Type", "text/html")
	                .withBody("Response Completed: GREEN OK")));

	    Monitor mon = new Monitor() ;
	    
	    List<ResultVo> resultList = mon.checkUrlList(new String[]  {"http://localhost:" + port + "/monitor/status" } ) ;
	    mon.output(resultList);
	    assertNotNull(resultList);
	    assertTrue(resultList.size() == 1) ;
	    assertEquals("Check whether result Status is Green", "GREEN", resultList.get(0).getStatusCheck());

	    verify(getRequestedFor(urlMatching("/monitor/status"))) ;
	            
	
}
}