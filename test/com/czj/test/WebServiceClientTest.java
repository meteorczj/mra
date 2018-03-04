package com.czj.test;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;

import com.czj.platform.webservice.HelloWorld;

public class WebServiceClientTest {
	
	@Test
	public void testHelloWorldSay(){
		JaxWsProxyFactoryBean jwpf = new JaxWsProxyFactoryBean();
		jwpf.setServiceClass(HelloWorld.class);
		
		jwpf.setAddress("http://localhost:9090/kangli/webservice/helloworld?wsdl");
		HelloWorld hw = (HelloWorld)jwpf.create();
		
		System.out.println(hw.sayHi("Hello"));
		System.out.println("count = " + hw.getCount());
	}
}
