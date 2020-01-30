package cart;

import service.CatalogRepeater;
import java.util.Objects;

public class LineItem {

    CatalogRepeater product;
    String color;
    Integer qty;

    public LineItem() {
    }

    public LineItem(CatalogRepeater product, String color) {
        this.product = product;
        this.color = color;
    }

    public CatalogRepeater getProduct() {
        return product;
    }
    public void setProduct(CatalogRepeater product) {
        this.product = product;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public Integer getQty() {
        return qty;
    }
    public void setQty(Integer qty) {
        this.qty = qty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineItem = (LineItem) o;
        return Objects.equals(product, lineItem.product) &&
                Objects.equals(color, lineItem.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, color);
    }
}
