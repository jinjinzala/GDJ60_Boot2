package com.iu.base.aoptest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class Card {
	
	//@AfterReturning("execution(* com.iu.base.aoptest.Transport.use*())")
	//@Before("execution(* com.iu.base.aoptest.Transport.use*())")
	//@AfterThrowing("execution(* com.iu.base.*.*Service.set*(..))")
	public Object cardCheck(ProceedingJoinPoint joinPoint) throws Throwable{
		log.error("탑승입니다");
		
		Object[] objs = joinPoint.getArgs();
		for(Object ob : objs) {
			log.warn("args============>{}",ob.toString());
		}
		Object obj = joinPoint.proceed();
		
		log.error("하차입니다");
		log.warn("object=====> {}",obj.toString());
		return obj;
	}
	
	
}
