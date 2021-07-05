package cart;

import store.Catalog;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

@SessionScoped
@Named
public class CartService implements Serializable {

    private Map<LineItem, Integer> LineItem;

    public CartService() {
        LineItem = new HashMap<>();
    }

    public void addProductQty(Catalog catalog, String color, int qty) {
        LineItem lineItem = new LineItem(catalog, color);
        LineItem.put(lineItem, LineItem.getOrDefault(lineItem, 0) + qty);
    }

    public void removeProductQty(Catalog catalog, String color, int qty) {
        LineItem lineItem = new LineItem(catalog, color);
        int currentQty = LineItem.getOrDefault(lineItem, 0);
        if (currentQty - qty > 0) LineItem.put(lineItem, currentQty - qty);
        else LineItem.remove(lineItem);
    }

    public List<LineItem> getLineItem() {
        LineItem.forEach(cart.LineItem::setQty);
        return new ArrayList<>(LineItem.keySet());
    }

}
