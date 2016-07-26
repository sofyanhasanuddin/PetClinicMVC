package org.sofyan.latihan.app.performance.monitoring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SimplePerformanceMonitoring {
	
	@Around("execution(* org.sofyan.latihan.app.service..*.*(..))")
	public Object logAroundService(ProceedingJoinPoint joinPoint) throws Throwable {

		PerformanceLog.start( Thread.currentThread().getName(), joinPoint.getSignature().getName() );
		
        try {
            return joinPoint.proceed();
        } finally {
        	PerformanceLog.end( Thread.currentThread().getName(), joinPoint.getSignature().getName());
        }

	}
	
	@Around("execution(* org.hibernate..*.*(..))")
	public Object logAroundController(ProceedingJoinPoint joinPoint) throws Throwable {
		
		PerformanceLog.start( Thread.currentThread().getName(), joinPoint.getSignature().getName() );

        try {
            return joinPoint.proceed();
        } finally {
            PerformanceLog.end( Thread.currentThread().getName(), joinPoint.getSignature().getName());
        }

	}
	
}
