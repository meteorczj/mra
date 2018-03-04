package com.czj.platform.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * demo演示
 * 
 * @author chuzj
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/demoController")
public class DemoController extends BaseController {

	/**
	 * 导航菜单跳转到菜单列表页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "demoList")
	public String demoList(HttpServletRequest request) {
		logger.debug("==================demoList");
		return "demo/demoList";
	}

}
