package com.lanwantec.manage.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@SuppressWarnings("restriction")
public class HTTPBasicAuthorizeAttribute implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = ((HttpServletResponse) response);
        HttpSession session = request1.getSession();
        System.out.println(request1.getContextPath());
        if (session.getAttribute("operNo")==null){
            response1.sendRedirect( "/manage/login");
        }else{
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

}