package bit.com.a.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import bit.com.a.model.MemberDto;

@Aspect
public class LogAop {

	@Around("within(bit.com.a.controller.*.*)")	
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable {
		String signatureStr = joinpoint.getSignature().toShortString();

		/*
		// session check
		HttpServletRequest request
			= ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		
		if(request != null) {
			HttpSession session = request.getSession();
			MemberDto login = (MemberDto)session.getAttribute("login");
			if(login == null) {
				return "redirect:/login.do";
			}
		}
		*/
		Object obj = joinpoint.proceed();	// 기능 실행		
		System.out.println("loggerAOP:" + signatureStr + " 메소드호출");
		
		return obj;			
	}
}




