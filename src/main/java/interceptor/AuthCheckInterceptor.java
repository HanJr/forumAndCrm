package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import variableObject.AuthInfo;

public class AuthCheckInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
			
			if(authInfo != null) {
				return true;
			}
		}
		
		response.sendRedirect(request.getContextPath() + "/login/loginForm");
		return false;
	}
}
