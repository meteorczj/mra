package com.czj.platform.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Scope("prototype")
@Controller
@RequestMapping("/supcanController")
public class SupcanController extends BaseController {
	
	@RequestMapping(params="supcanList")
	public String supcanList(HttpServletRequest request) {
		
		return "supcan/supcanList";
	}

}
