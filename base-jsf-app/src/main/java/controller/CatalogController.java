package controller;

import cart.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.CatalogService;
import store.Catalog;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class CatalogController implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(CatalogController.class);

//    @EJB
//    private CatalogRepository catalogRepository;

    @EJB
    private CatalogService catalogService;

    @EJB
    private CartService cartService;

    private Catalog catalog;
    private List<Catalog> catalogs;

    public void preloadCatalog(ComponentSystemEvent componentSystemEvent) {
        this.catalogs = catalogService.findAll();
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public List<Catalog> getAllCatalog() {
        return catalogs;
    }

    public String createProduct() {
        this.catalog = new Catalog();
        return "/product.xhtml?faces-redirect=true";
    }

    public String saveProduct() {
        if (catalog.getId() == null) {
            catalogService.insert(catalog);
        } else {
            catalogService.update(catalog);
        }
        return "/catalog.xhtml?faces-redirect=true";
    }

    public void deleteProduct(Catalog catalog) {
        this.catalog = catalog;
        catalogService.delete(catalog.getId());
        logger.info("Deleting Product");
        // return "/catalog.xhtml?faces-redirect=true";
    }

    public String editCatalog(Catalog catalog) {
        this.catalog = catalog;
        return "/product.xhtml?faces-redirect=true";
    }

    public void addToCart(Catalog catalog) {
        cartService.addProductQty(catalog, "Green", 1);
    }

}
