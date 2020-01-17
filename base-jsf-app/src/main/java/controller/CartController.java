package controller;

import store.Catalog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Map;
import java.util.TreeMap;

@ApplicationScoped
@Named
public class CartController {
    Map<Integer, Catalog> cart = new TreeMap<>();

    public CartController() {
    }

}
