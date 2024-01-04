package ru.itis.security;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AutentificationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        if (httpServletRequest.getServletPath().equals("/usercheck")
            || httpServletRequest.getServletPath().equals("/regpage")
            || httpServletRequest.getServletPath().equals("/registration")
            || httpServletRequest.getServletPath().startsWith("/resources")
        ) {
            filterChain.doFilter(request, response);
        } else {


            HttpSession session = ((HttpServletRequest) request).getSession(false);

            if (session != null
                    && session.getAttribute("visitorid") != null) {
                httpServletRequest.getSession().setAttribute("visitorid", session.getAttribute("visitorid"));

                filterChain.doFilter(request, response);
            } else {
                request.getRequestDispatcher("/login").forward(request, response);

            }
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
