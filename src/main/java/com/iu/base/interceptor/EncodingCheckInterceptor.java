package com.iu.base.interceptor;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.iu.base.board.qna.QnaDAO;
import com.iu.base.member.MemberRoleVO;
import com.iu.base.member.MemberVO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class EncodingCheckInterceptor implements HandlerInterceptor {

   @Autowired
   private QnaDAO qnaDAO;
   
      @Override
      public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
    	  
    	  request.setCharacterEncoding("UTF-8");
    	  response.setCharacterEncoding("UTF-8");
      
      return true;
      }
      
      
      public void postHandle(HttpServletRequest request, HttpServletResponse response,ModelAndView modelAndView)
            throws Exception {
    	  
    	
      }
}