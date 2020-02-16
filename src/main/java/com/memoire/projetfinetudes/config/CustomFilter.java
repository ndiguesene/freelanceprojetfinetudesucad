package com.memoire.projetfinetudes.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// @Component
public class CustomFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException { }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = null;
        try {
            res = (HttpServletResponse) response;
            chain.doFilter(request, response);
            if (res.getStatus() == 404) {
                ((HttpServletResponse) response).sendRedirect("/login");
   /*             RequestDispatcher dd = request.getRequestDispatcher("error.jsp");
                dd.forward(request, response);*/
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            request.setAttribute("errorMessage", ex.getMessage());
        }
    }
}
