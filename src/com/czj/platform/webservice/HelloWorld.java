package com.czj.platform.webservice;

import javax.jws.WebService;

@WebService
public interface HelloWorld {
	
	String sayHi(String message);
	
	int getCount();
	
}
