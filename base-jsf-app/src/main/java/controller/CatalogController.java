package controller;

import cart.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import store.Catalog;
import store.CatalogRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@SessionScoped
@Named
public class CatalogController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(CatalogController.class);

    @Inject
    private CatalogRepository catalogRepository;
    @Inject
    private CartService cartService;

    private Catalog catalog;

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public List<Catalog> getAllCatalog() throws SQLException {
        return catalogRepository.findAll();
    }

    public String createProduct() {
        this.catalog = new Catalog();
        return "/product.xhtml?faces-redirect=true";
    }

    public String saveProduct() throws SQLException {
        if (catalog.getId() == null) {
            catalogRepository.insert(catalog);
        } else {
            catalogRepository.update(catalog);
        }
        return "/catalog.xhtml?faces-redirect=true";
    }

    public void deleteProduct(Catalog catalog) throws SQLException {
        logger.info("Deleting Product");
        catalogRepository.delete(catalog.getId());
        // return "/catalog.xhtml?faces-redirect=true";
    }

    public String editCatalog(Catalog catalog) {
        this.catalog = catalog;
        return "/product.xhtml?faces-redirect=true";
    }

    public void addToCart(Catalog catalog) {
        // cartController.addToCart(catalog);
        cartService.addProductQty(catalog, "Green", 1);
    }

}
