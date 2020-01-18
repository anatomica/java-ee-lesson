package controller;

import store.Catalog;
import store.CatalogRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@ApplicationScoped
@Named
public class CartController {

    private Map<String, HashSet<String>> cart;
    private ArrayList<Integer> nums;
    private String ID;
    private String Name;
    private String Description;
    private String Price;

    @Inject
    CatalogRepository catalogRepository;

    public CartController() {
        cart = new HashMap<>();
        nums = new ArrayList<>();
    }

    public Map<String, HashSet<String>> getCart() {
        return cart;
    }

    public String getID() {
        return ID;
    }
    public String getName() {
        return Name;
    }
    public String getDescription() {
        return Description;
    }
    public String getPrice() {
        return Price;
    }

    public void addToCart(Catalog catalog) {
//        HashSet<String> ID = cart.getOrDefault("ID", new HashSet<>());
//        ID.add(catalog.getId().toString());
//        cart.put("ID", ID);
//        HashSet<String> Name = cart.getOrDefault("Name", new HashSet<>());
//        Name.add(catalog.getName());
//        cart.put("Name", Name);
//        HashSet<String> Description = cart.getOrDefault("Description", new HashSet<>());
//        Description.add(catalog.getDescription());
//        cart.put("Description", Description);
//        HashSet<String> Price = cart.getOrDefault("Price", new HashSet<>());
//        Price.add(catalog.getPrice().toString());
//        cart.put("Price", Price);

        nums.add(catalog.getId().intValue());
    }

    public void removeFromCart(Catalog catalog) {
        nums.removeIf(num -> num == catalog.getId().intValue());
    }

    public List<Catalog> getAllCart() throws SQLException {
        List<Catalog> res = new ArrayList<>();
        return catalogRepository.findChosen(res, nums);
    }
}
