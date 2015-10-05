package com.epayroll.spring.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Krishnat Pawar
 */
@Aspect
@Component
public class ApplicationLogger {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Pointcut("within(com.epayroll.service.impl.*)")
	public void operationPointCut() {
	}

	@AfterThrowing(pointcut = "operationPointCut()", throwing = "error")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
		StringBuilder exceptionBuilder = new StringBuilder("Exception occured in :: Class :: ");
		exceptionBuilder.append(joinPoint.getSignature().getDeclaringType().getName());
		exceptionBuilder.append(":: for :: Method :: ");
		exceptionBuilder.append(joinPoint.getSignature().getName());
		exceptionBuilder.append("() :: Detailed Exception is :: ");
		logger.error(exceptionBuilder.toString(), error);
	}

}
