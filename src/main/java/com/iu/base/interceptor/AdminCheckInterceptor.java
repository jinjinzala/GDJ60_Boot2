package com.iu.base.interceptor;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.iu.base.member.MemberRoleVO;
import com.iu.base.member.MemberVO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class AdminCheckInterceptor implements HandlerInterceptor {

	   @Override
	      public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	            throws Exception {
	      
	         
	            HttpSession session = request.getSession();
	            MemberVO memberVO = (MemberVO)session.getAttribute("member");
	            
	            List<MemberRoleVO> roleVOs = memberVO.getRoleVOs();
	            
	            
	            for(MemberRoleVO roleVO:roleVOs) {
	               if(roleVO.getRoleName().equals("ROLE_ADMIN")) {
	                  return true;
	               }
	            
	            }
	            request.setAttribute("message","권한이 없음");
	            request.setAttribute("url","../");
	            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/result.jsp");
	            view.forward(request, response);
	            return false;
	         
	      }
	   
	   
}