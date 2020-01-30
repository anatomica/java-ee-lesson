package service;

import store.Catalog;
import java.util.List;

public interface CatalogService {

    void insert(CatalogRepeater product);

    void update(CatalogRepeater product);

    void delete(long id);

    Catalog findById(long id);

    List<CatalogRepeater> findAll();


}
