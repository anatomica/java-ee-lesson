package controller;

import cart.CartService;
import cart.LineItem;
import service.CatalogRepr;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class CartController implements Serializable {

    @Inject
    CartService cartService;

    public List<LineItem> getLineItem() {
        return cartService.getLineItem();
    }

    public void removeLineItem(CatalogRepr product) {
        cartService.removeProductQty(product, "Green", 1);
    }

    public void removeAllLineItem(CatalogRepr product) {
        cartService.removeProductQty(product, "Green", 1000);
    }
}
