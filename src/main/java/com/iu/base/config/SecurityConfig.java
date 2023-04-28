package com.iu.base.config;

import javax.annotation.security.PermitAll;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;

import com.iu.base.security.UserSuccessHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	
	//public 을 선언하면 default로 바꾸라는 메세지 출력
	@Bean
	WebSecurityCustomizer webSecurityConfig() {
		return web ->  web
				   .ignoring()
				   .antMatchers("/images/**")
				   .antMatchers("/js/**")
				   .antMatchers("/css/**")
				   .antMatchers("/favicon/**");
//				   .antMatchers("/resources/**");
	}
	
	@Bean
	SecurityFilterChain fiterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			 .cors()
			 .and()
			 .csrf()
			 .disable()
			 .authorizeRequests()
			 .antMatchers("/").permitAll()
			 //url과 권한 매칭 
			.antMatchers("/member/join").permitAll()
			.antMatchers("/notice/add").hasRole("ADMIN")
			.antMatchers("/notice/update").hasRole("ADMIN")
			.antMatchers("/notice/delete").hasRole("ADMIN")
			.antMatchers("/notice/*").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/qna/add").hasAnyRole("ADMIN","MEMBER","MANAGER")
			//이외 나머지는 로그인해야 볼수 있어요 .anyRequest().authenticated();
			//.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/member/login")
			//.defaultSuccessUrl("/")
			//.logoutSuccessUrl (new UserSuccessHandler())
			.successHandler(new UserSuccessHandler())
			.failureUrl("/member/login")
			.permitAll()
			.and()
			.logout()
			.logoutUrl("/member/logout")
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.permitAll()
			;
			//.anyRequest().authenticated();
			return httpSecurity.build();
		
	}
	@Bean
	public  PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}
