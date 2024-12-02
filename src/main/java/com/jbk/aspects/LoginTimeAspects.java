package com.jbk.aspects;

import java.time.LocalTime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoginTimeAspects {
	
	Logger logger = LoggerFactory.getLogger(LoginTimeAspects.class);
	
//	@Before("execution(* com.jbk.controller.AuthController.loginUser(..))")
//	public void captureStartTime() {
//		System.out.println("start time ="+LocalTime.now());
//	}
//	
//	@After("execution(* com.jbk.controller.AuthController.loginUser(..))")
//	public void captureEndTime() {
//		System.out.println("End time ="+LocalTime.now());
//	}
	
	@Around("execution(* com.jbk.controller.AuthController.loginUser(..))")
	public Object trackExecutionTime(ProceedingJoinPoint joinpoint) throws Throwable{
		
		
		long startTime = System.currentTimeMillis();
		Object obj = joinpoint.proceed();
		long endTime = System.currentTimeMillis();
		
		logger.info("Execution Time="+(endTime-startTime));
		return obj;
		
		
	}
//	com.jbk.controller.AuthController.loginUser(LoginRequest)
	

}
