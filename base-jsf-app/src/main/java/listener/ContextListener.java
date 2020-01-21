package listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import store.CatalogRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class ContextListener implements ServletContextListener {
    private Logger logger = LoggerFactory.getLogger(ContextListener.class);

    @Override
    public void contextInitialized (ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        logger.info( "Context initialization: " + ctx.getContextPath());

        String jdbcConnectionString = ctx.getInitParameter( "jdbcConnectionString" );
        String dbUsername = ctx.getInitParameter( "dbUsername" );
        String dbPassword = ctx.getInitParameter( "dbPassword" );

        if (isNotNullOrEmpty(jdbcConnectionString) || isNotNullOrEmpty(dbUsername)) {
            logger.error( "Connection string and DB username must be specified" );
            return;
        }

        try {
            Connection conn = DriverManager.getConnection(jdbcConnectionString, dbUsername, dbPassword);
            ctx.setAttribute("connection", conn);
        } catch (SQLException ex) {
            logger.error("", ex);
        }
    }

    @Override
    public void contextDestroyed (ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        Connection conn = (Connection) context.getAttribute( "jdbcConnection" );
        if (conn == null ) {
            return ;
        }
        try {
            conn.close();
        } catch (SQLException e) {
            logger.error( "" , e);
        }
    }
    private boolean isNotNullOrEmpty (String str) {
        return str != null && str.isEmpty();
    }
}