package listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.CatalogRepository;
import store.Catalog;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;
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
            CatalogRepository catalogRepository = new CatalogRepository(conn);
            ctx.setAttribute("connection", conn);
            ctx.setAttribute("catalogRepository", catalogRepository);

            if (catalogRepository.findAll().size() == 0) {
                catalogRepository.insert(new Catalog(-1L, "Product1", "Desc1", new BigDecimal(10)));
                catalogRepository.insert(new Catalog(-1L, "Product2", "Desc2", new BigDecimal(20)));
                catalogRepository.insert(new Catalog(-1L, "Product3", "Desc3", new BigDecimal(30)));
                catalogRepository.insert(new Catalog(-1L, "Product4", "Desc4", new BigDecimal(40)));
                catalogRepository.insert(new Catalog(-1L, "Product5", "Desc5", new BigDecimal(50)));
            }
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