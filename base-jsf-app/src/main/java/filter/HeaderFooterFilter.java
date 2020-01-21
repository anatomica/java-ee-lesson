package filter;

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
        filterConfig.getServletContext().getRequestDispatcher( "/header.xhtml" ).include(req, resp);
        chain.doFilter(req, resp);
        filterConfig.getServletContext().getRequestDispatcher( "/footer.xhtml" ).include(req, resp);
    }

    @Override
    public void destroy () {
    }

}
