package com.fz.Interceptor;

import com.fz.model.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
public class LoginInterceptor  implements HandlerInterceptor{
    private static String[] IGNORE_URL = new String[]{"default.jsp","foot.jsp",
            "head.jsp","menu.jsp","list.jsp","save.jsp","index.jsp"};
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String requestUrl = httpServletRequest.getRequestURI();
        if("/user/login.do".equals(requestUrl)){
            //如果是登录请求，直接放过
            return true;
        }
        for (String url : IGNORE_URL){
            if(requestUrl.contains(url)){
                System.out.println("the uri : "+requestUrl);
                User user = (User) httpServletRequest.getSession().getAttribute("currentUser");
                if(null == user){ //user session 为空
                    httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/login.jsp");
                    return false;
                }
                break;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
