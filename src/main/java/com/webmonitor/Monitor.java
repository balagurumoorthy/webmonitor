package com.webmonitor;

import java.util.ArrayList;
import java.util.List;

import com.webmonitor.vo.ResultVo;

public class Monitor {

	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	  /**  Connect to a URL,
	   *    check the status is 200 or others
	   *    check the if 200 check the response content**/
		
		
	}
	
	
	public List<ResultVo> checkUrlList (String args[]) {
		HttpClient client = new HttpClient() ;
		List<ResultVo> resultList = new ArrayList<ResultVo>() ;
		
		for(int arg = 0 ;  arg < args.length; arg++) {
			resultList.add(client.checkUrl(args[arg])) ;
		}
		
		return resultList;
	}
	
	public void output(List<ResultVo> resultList) {
		
		resultList.forEach(resultVo -> System.out.format("|%16d|%30s|%5s|%-16d|" , 
									resultVo.getEpoch() , resultVo.getCheckUrl() , resultVo.getStatusCheck() , resultVo.getResponseTimeInms()  )   ) ;
			
	}
	
	
	
}


