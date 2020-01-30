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
    public List<Catalog> findAll() {
        return catalogRepository.findAll().stream().map(product ->
                new Catalog(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public void insert (Catalog product) {
        catalogRepository.insert(product);
    }

    @Override
    public void update(Catalog product) {
        catalogRepository.update(product);
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
