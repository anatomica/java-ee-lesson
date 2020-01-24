package controller;

import cart.CartService;
import cart.LineItem;
import store.Catalog;
import store.CatalogRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SessionScoped
@Named
public class CartController implements Serializable {

    @Inject
    CartService cartService;

    public List<LineItem> getLineItem() {
        return cartService.getLineItem();
    }

    public void removeLineItem(Catalog catalog) {
        cartService.removeProductQty(catalog, "Green", 1);
    }

    public void removeAllLineItem(Catalog catalog) {
        cartService.removeProductQty(catalog, "Green", 1000);
    }
}
