package cart;

import store.Catalog;

import java.util.Objects;

public class LineItem {

    Catalog catalog;
    String color;
    Integer qty;

    public LineItem() {
    }

    public LineItem(Catalog catalog, String color) {
        this.catalog = catalog;
        this.color = color;
    }

    public Catalog getCatalog() {
        return catalog;
    }
    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
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
        return Objects.equals(catalog, lineItem.catalog) &&
                Objects.equals(color, lineItem.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catalog, color);
    }

}
