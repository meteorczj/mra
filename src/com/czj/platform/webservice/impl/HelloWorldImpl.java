package com.czj.platform.webservice.impl;


import javax.annotation.Resource;
import javax.jws.WebService;

import org.springframework.stereotype.Component;

import com.czj.platform.dao.ModuleDao;
import com.czj.platform.webservice.HelloWorld;



@Component
@WebService(endpointInterface="com.czj.platform.webservice.HelloWorld",serviceName="helloworld")
public class HelloWorldImpl implements HelloWorld{
	
	@Resource(name="moduleDao")
	private ModuleDao moduleDao;
	
	public String sayHi(String message) {
		return "Hi " + message + " !";
	}
	
	public int getCount() {
		int count = this.moduleDao.selectModuleCount(null);
		return count;
	}
	
}
