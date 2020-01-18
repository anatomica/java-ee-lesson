package controller;

import store.Catalog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.*;

@ApplicationScoped
@Named
public class CartController {

    private Map<String, HashSet<String>> cart;
    // private Map<String, String> cart;
    private String ID;
    private String Name;
    private String Description;
    private String Price;

    public CartController() {
        cart = new HashMap<>();
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
        HashSet<String> ID = cart.getOrDefault("ID", new HashSet<>());
        ID.add(catalog.getId().toString());
        cart.put("ID", ID);
        HashSet<String> Name = cart.getOrDefault("Name", new HashSet<>());
        Name.add(catalog.getName());
        cart.put("Name", Name);
        HashSet<String> Description = cart.getOrDefault("Description", new HashSet<>());
        Description.add(catalog.getDescription());
        cart.put("Description", Description);
        HashSet<String> Price = cart.getOrDefault("Price", new HashSet<>());
        Price.add(catalog.getPrice().toString());
        cart.put("Price", Price);


//        cart.put("ID", catalog.getId().toString());
//        cart.put("Name", catalog.getName());
//        cart.put("Description", catalog.getDescription());
//        cart.put("Price", catalog.getPrice().toString());

    }
}
