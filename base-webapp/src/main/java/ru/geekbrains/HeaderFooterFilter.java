package ru.geekbrains;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter (urlPatterns = "/*" )
public class HeaderFooterFilter implements Filter {

    private transient FilterConfig filterConfig;

    @Override
    public void init (FilterConfig filterConfig) throws ServletException {
        this .filterConfig = filterConfig;
    }
    @Override
    public void doFilter (ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        // resp.getWriter().println("<h1>Header HTTP</h1>");
        filterConfig.getServletContext().getRequestDispatcher( "/header.html" ).include(req, resp);
        chain.doFilter(req, resp);
        // resp.getWriter().println("<h1>Footer HTTP</h1>");
        filterConfig.getServletContext().getRequestDispatcher( "/footer.html" ).include(req, resp);
    }

    @Override
    public void destroy () {
    }

}
