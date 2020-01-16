package store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet(name = "CatalogController", urlPatterns = {"/catalog", "/create", "/update", "/edit", "/delete"})
public class CatalogController extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(CatalogController.class);
    private CatalogRepository catalogRepository;

    @Override
    public void init() throws ServletException {
        this.catalogRepository = (CatalogRepository) getServletContext().getAttribute("catalogRepository");
        if (catalogRepository == null) {
            throw new ServletException("CatalogRepository not created");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            switch (req.getServletPath()) {
                case "/catalog":
                    req.setAttribute("products", catalogRepository.findAll());
                    getServletContext().getRequestDispatcher("/catalog.xhtml").forward(req, resp);
                    break;
                case "/create":
                    showCreateProductPage(req, resp);
                    break;
                case "/edit":
                    showEditProductPage(req, resp);
                    break;
                case "/delete":
                    deleteProduct(req, resp);
                    break;
            }
        } catch (SQLException e) {
            logger.error("", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/create":
                createProduct(req, resp);
                break;
            case "/update":
                updateProduct(req, resp);
                break;
            case "/edit":
                updateProduct(req, resp);
                break;
            case "/delete":
                deleteProduct(req, resp);
                break;
            default:
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            catalogRepository.insert(new Catalog(
                    -1L,
                    req.getParameter("name"),
                    req.getParameter("description"),
                    new BigDecimal(req.getParameter("price"))
            ));
            resp.sendRedirect(getServletContext().getContextPath());
        } catch (SQLException ex) {
            logger.error("", ex);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (NumberFormatException ex) {
            logger.error("", ex);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            catalogRepository.update(new Catalog(
                    Long.parseLong(req.getParameter("id")),
                    req.getParameter("name"),
                    req.getParameter("description"),
                    new BigDecimal(req.getParameter("price"))
            ));
            resp.sendRedirect(getServletContext().getContextPath());
        } catch (SQLException ex) {
            logger.error("", ex);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (NumberFormatException ex) {
            logger.error("", ex);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) {
        try {
            catalogRepository.delete(Long.parseLong(req.getParameter("id")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showEditProductPage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (Exception ex) {
            logger.error("", ex);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        Catalog product;
        try {
            product = catalogRepository.findById(id);
        } catch (SQLException ex) {
            logger.error("", ex);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        req.setAttribute("product", product);
        req.setAttribute("action", "edit");
        getServletContext().getRequestDispatcher("/editProduct.xhtml").forward(req, resp);
    }

    private void showCreateProductPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("product", new Catalog());
        req.setAttribute("action", "create");
        getServletContext().getRequestDispatcher("/editProduct.xhtml").forward(req, resp);
    }
}
