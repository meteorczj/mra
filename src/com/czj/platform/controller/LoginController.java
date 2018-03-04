package com.czj.platform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.czj.platform.entity.Constants;
import com.czj.platform.entity.User;
import com.czj.platform.entity.json.AjaxJson;
import com.czj.platform.service.UserService;

@Controller
@Scope("prototype")
@RequestMapping("/loginController")
public class LoginController extends BaseController {
	
	@Autowired
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(params = "toLogin")
	public String toLoginJsp(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(Constants.USER_INFO);
		
		//用户登录后，自动跳转到主页
		if(null != user) {
			return "main/main";
		} else {
			return "login/login";
		}
	}

	@RequestMapping(params = "checkuser")
	@ResponseBody
	public Object checkuser(HttpServletRequest request, HttpServletResponse response, User entity) {
		AjaxJson result = new AjaxJson();
		try {
			// 从cookie中取密码，这个密码不是用户的真实密码，是最上面的默认密码
	        //Cookie passwordCookie = WebUtils.getCookie(request, "password");
	        //logger.debug("======================passwordCookie:" + passwordCookie.getName() + "," + passwordCookie.getValue());
			
			User user = new User();
			user.setUsername(entity.getUsername());
			user = this.userService.selectUser(user);
			if(null != user && StringUtils.hasText(user.getId())) {
				if(logger.isDebugEnabled())
					logger.debug("==============username:" + user.getUsername() + ",password:" + user.getPassword());
				
				if(!user.getPassword().equals(entity.getPassword())) {
					result.setSuccess(false);
					result.setMsg("密码错误!");
				}
				
				request.getSession().setAttribute(Constants.USER_INFO, user);
			} else {
				result.setSuccess(false);
				result.setMsg("用户名错误!");
			}
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("登录失败!");
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "login")
	public String login(HttpServletRequest request) {
		boolean isLogin = true;
		if (isLogin) {
			return "main/main";
		} else {
			return "login/login";
		}

	}
	
	/**
	 * 用户退出
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(null != session) {
			session.removeAttribute(Constants.USER_INFO);
			session.invalidate();
		}
		return "login/login";
	}

	/**
	 * 首页跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "home")
	public ModelAndView home(HttpServletRequest request) {
		return new ModelAndView("main/home");
	}

}
