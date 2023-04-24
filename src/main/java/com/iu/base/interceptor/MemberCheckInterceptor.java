package com.iu.base.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.iu.base.member.MemberVO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class MemberCheckInterceptor implements HandlerInterceptor {

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
				
		log.info("=============================interceptor=======================");
		
		HttpSession session = request.getSession();
		
		Object memberVO= session.getAttribute("member");
		
		if(memberVO != null) {
			return true;
		}else {
			//response.sendRedirect("/member/login");
			request.setAttribute("message", "로그인이 필요합니다");
			request.setAttribute("url", "/member/login");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/result.jsp");
			view.forward(request, response);
			return false;
		}
	}
}
