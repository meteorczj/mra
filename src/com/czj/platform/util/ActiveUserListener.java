package com.czj.platform.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

/**
 * 获取在线用户数
 * @author Administrator
 *
 */
public class ActiveUserListener implements HttpSessionListener {
	private static int sessionCount=0;
	private static Map<String, Object> sessionMap = new HashMap<String, Object>();
	private static Logger logger = Logger.getLogger(ActiveUserListener.class);

	public void sessionCreated(HttpSessionEvent httpsessionevent) {
		sessionCount ++;
		logger.debug("---------------增加session，当前用户数：" + sessionCount);
		HttpSession httpSession = httpsessionevent.getSession();
		httpSession.getServletContext().setAttribute("sessionCount", sessionCount);
		
		httpSession.setMaxInactiveInterval(1000);
		
		String sessionId = httpSession.getId();
		sessionMap.put(sessionId, httpSession);
	}

	public void sessionDestroyed(HttpSessionEvent httpsessionevent) {
		sessionCount --;
		HttpSession httpSession = httpsessionevent.getSession();
		httpSession.getServletContext().setAttribute("sessionCount", sessionCount);
		
		logger.debug("---------------销毁session，当前用户数：" + sessionCount);
		
		String sessionId = httpsessionevent.getSession().getId();
		sessionMap.remove(sessionId);
	}

	public static int getSessionCount() {
		return sessionCount;
	}

	public static void setSessionCount(int sessionCount) {
		ActiveUserListener.sessionCount = sessionCount;
	}

}
