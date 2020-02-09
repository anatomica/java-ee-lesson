package store;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface CatalogRepositoryWS {

    @WebMethod
    List<Catalog> findAll();
}
