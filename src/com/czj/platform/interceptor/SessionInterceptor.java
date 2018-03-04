package com.czj.platform.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.czj.platform.entity.Constants;
import com.czj.platform.entity.User;

/**
 * 登录拦截器，如果未登录，则跳转到登录页面
 * @author Chu,zhujun
 * @version 2015年9月23日 下午3:47:50
 */
public class SessionInterceptor implements HandlerInterceptor{
	
	private static Logger logger = Logger.getLogger(SessionInterceptor.class);
	
	private String loginPage;
	
	private List<String> excludeUrls;

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		String requestPath = request.getServletPath();
		
		//设置项目显示标题
		request.setAttribute("title", Constants.TITLE);
		
		//this.getRequestPath(request);
		if(logger.isDebugEnabled()) 
			logger.debug("过滤的请求路径 requestPath:" + requestPath);
		
		//如果该请求不在拦截范围内，直接返回true
		if(this.excludeUrls.contains(requestPath)) {
			return true;
		}
		
		User user = (User)request.getSession().getAttribute(Constants.USER_INFO);
		//用户未登录，跳转到登录页面
		if(null == user) {
			response.sendRedirect(this.getCtxPath(request) + this.getLoginPage());
			//response.sendRedirect(request.getContextPath() + this.loginPage);
			return false;
		}
		return true;
	}
	
	public String getCtxPath(HttpServletRequest request) {
		StringBuffer ctx = new StringBuffer();
		ctx.append(request.getScheme()).append("://").append(request.getServerName()).append(":").append(request.getServerPort());
		ctx.append(request.getContextPath());
		return ctx.toString();
	}
	
	public String getRequestPath(HttpServletRequest request) {
		String requestUrl = request.getRequestURI();
		String queryString = request.getQueryString();
		logger.debug("-------------------------requestUrl:" + requestUrl);
		logger.debug("-------------------------queryString:" + queryString);
		return null;
	}

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

}
