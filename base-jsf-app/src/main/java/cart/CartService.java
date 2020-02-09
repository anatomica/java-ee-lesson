package cart;

import service.CatalogRepeater;
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

    public void addProductQty(CatalogRepeater product, String color, int qty) {
        LineItem lineItem = new LineItem(product, color);
        LineItem.put(lineItem, LineItem.getOrDefault(lineItem, 0) + qty);
    }

    public void removeProductQty(CatalogRepeater product, String color, int qty) {
        LineItem lineItem = new LineItem(product, color);
        int currentQty = LineItem.getOrDefault(lineItem, 0);
        if (currentQty - qty > 0) LineItem.put(lineItem, currentQty - qty);
        else LineItem.remove(lineItem);
    }

    public List<LineItem> getLineItem() {
        LineItem.forEach(cart.LineItem::setQty);
        return new ArrayList<>(LineItem.keySet());
    }

}
