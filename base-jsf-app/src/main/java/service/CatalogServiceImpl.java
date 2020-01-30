package service;

import store.Catalog;
import store.CatalogRepository;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
// @WebService (endpointInterface = "service.CatalogService", serviceName = "CatalogServiceImpl")
public class CatalogServiceImpl implements CatalogService {

    @EJB
    private CatalogRepository catalogRepository;

    @Override
    public List<CatalogRepeater> findAll() {
        return catalogRepository.findAll().stream().map(product ->
                new CatalogRepeater(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public void insert (CatalogRepeater product) {
        Catalog catalog = new Catalog();
        catalog.setId(product.getId());
        catalog.setName(product.getName());
        catalog.setDescription(product.getDescription());
        catalog.setPrice(product.getPrice());
        catalogRepository.insert(catalog);
    }

    @Override
    public void update(CatalogRepeater product) {
        Catalog catalog = new Catalog();
        catalog.setId(product.getId());
        catalog.setName(product.getName());
        catalog.setDescription(product.getDescription());
        catalog.setPrice(product.getPrice());
        catalogRepository.update(catalog);
    }

    @Override
    public void delete(long id) {
        catalogRepository.delete(id);
    }

    @Override
    public Catalog findById(long id) {
        return catalogRepository.findById(id);
    }
}
