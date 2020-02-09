package controller;

import cart.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.CatalogRepeater;
import service.CatalogService;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
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

    @Inject
    private CartService cartService;

    private CatalogRepeater product;
    private List<CatalogRepeater> products;

    public void preloadCatalog(ComponentSystemEvent componentSystemEvent) {
        this.products = catalogService.findAll();
    }

    public CatalogRepeater getProduct() {
        return product;
    }

    public void setProduct(CatalogRepeater product) {
        this.product = product;
    }

    public List<CatalogRepeater> getAllCatalog() {
        return products;
    }

    public String createProduct() {
        this.product = new CatalogRepeater();
        return "/product.xhtml?faces-redirect=true";
    }

    public String saveProduct() {
        if (product.getId() == null) {
            catalogService.insert(product);
        } else {
            catalogService.update(product);
        }
        return "/catalog.xhtml?faces-redirect=true";
    }

    public void deleteProduct(CatalogRepeater product) {
        this.product = product;
        catalogService.delete(product.getId());
        logger.info("Deleting Product");
        // return "/catalog.xhtml?faces-redirect=true";
    }

    public String editCatalog(CatalogRepeater product) {
        this.product = product;
        return "/product.xhtml?faces-redirect=true";
    }

    public void addToCart(CatalogRepeater product) {
        cartService.addProductQty(product, "Green", 1);
    }

}
