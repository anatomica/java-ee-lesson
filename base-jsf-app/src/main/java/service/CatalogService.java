package service;

import store.Catalog;
import java.util.List;

public interface CatalogService {

    void insert(CatalogRepr product);

    void update(CatalogRepr product);

    void delete(long id);

    Catalog findById(long id);

    List<CatalogRepr> findAll();


}
