package com.webmonitor;

import java.util.ArrayList;
import java.util.List;

import com.webmonitor.vo.ResultVo;

public class Monitor {

	
	
	
	
	
	public static void main(String[] args) {
		
		Monitor monitor = new Monitor() ;
		List<ResultVo> resultList = monitor.checkUrlList(args) ;
		monitor.output(resultList);
		
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


