package com.webmonitor.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultVo {

		private String checkUrl;
		private long epoch ;
		private String statusCheck ;
		private long responseTimeInms ;
		public ResultVo(String checkUrl) {
			super();
			this.checkUrl = checkUrl;
		}
		
		
	
}
