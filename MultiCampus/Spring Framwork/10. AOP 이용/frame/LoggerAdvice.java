package com.frame;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

public class LoggerAdvice {
	public void beforeLog(JoinPoint jp){
		Object [] arg = jp.getArgs();
		Signature si = jp.getSignature();
		
		Date d = new Date();
		System.out.println(d.toString()+
				" Before Log:"+si.getName());
	}
	public void afterLog(JoinPoint jp){
		Signature si = jp.getSignature();
		System.out.println("After Log:"+si.getName());
	}
	public void afterReturnLog(JoinPoint jp,Object returnVal){
		Signature si = jp.getSignature();
		System.out.println("afterReturnLog:"+si.getName()+":"+returnVal);
	}
	public void afterEx(JoinPoint jp, Exception exObj){
		Signature si = jp.getSignature();
		System.out.println("afterEx Log:"+si.getName());
		System.out.println("Exception:"+exObj.getMessage());
	}
	public Object around(ProceedingJoinPoint process){
		Object result = null;
		Signature si = process.getSignature();	
		String className = process.getTarget().toString(); 
		long start = System.currentTimeMillis();
		//System.out.println("Before:"+si.getName()+" "+className);
		try {
			result = process.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println(
				si.getName()+" 함수 실행 시간 "+(end-start)+"ms");
		//System.out.println("After:"+si.getName()+" "+className);
		return result;
	}
}













