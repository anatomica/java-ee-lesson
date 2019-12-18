package ru.geekbrains;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.*;
import java.io.IOException;
import java.io.Serializable;

public class HelloWorldServlet implements Servlet, Serializable {

    private static Logger logger = LoggerFactory.getLogger(HelloWorldServlet.class);
    private transient ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this .config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        logger.info("New request");
        res.getWriter().println("<h1>HelloWorldServlet content</h1>");
    }

    @Override
    public String getServletInfo() {
        return "HelloWorldServlet";
    }

    @Override
    public void destroy() {
        logger.info( "Servlet {} destroyed" , getServletInfo());
    }
}
