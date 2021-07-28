package com.revature.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Pointcut;

public class LoggingAspect {
	private Logger logger = LogManager.getLogger();
	
	@AfterThrowing(pointcut="execution(* com.revature.shop.*.*(..))", throwing="error")
	public void logAfterFailure(JoinPoint jp, Throwable error) {
		logger.error(error.toString());
	}
	
	@Pointcut
	public void logMethod() {}
}
