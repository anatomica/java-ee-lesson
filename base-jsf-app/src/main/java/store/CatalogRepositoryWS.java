package store;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface CatalogRepositoryWS {

    @WebMethod
    void insert(Catalog product);

    @WebMethod
    void update(Catalog product);

    @WebMethod
    void delete(long id);

    @WebMethod
    Catalog findById(long id);

    @WebMethod
    List<Catalog> findAll();
}
