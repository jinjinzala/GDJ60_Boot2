package com.iu.base.security;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.logging.ErrorManager;

import javax.management.BadAttributeValueExpException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserLoginFailHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		   String errorMessage="";
		log.error("====================== {} =======================",exception);
		log.error("====================== {} =======================",exception.getMessage());
		if(exception instanceof BadCredentialsException) {		
			errorMessage="비밀번호가 일치하지 않습니다";
		}else if(exception instanceof InternalAuthenticationServiceException){
			errorMessage="아이디가 일치하지 않습니다";
		}else if(exception instanceof DisabledException){errorMessage="유효하지 않은 사용자입니다";
		//enabled가 false인 경우
		}else{errorMessage="로그인시류ㅐ";
		
		}
		
		
		errorMessage = URLEncoder.encode(errorMessage,"UTF-8");
		response.sendRedirect("/member/login?errorMessage="+errorMessage);
		
	}

	
		
}
