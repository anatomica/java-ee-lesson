package store;

import javax.ejb.Remote;
import java.util.List;
import java.util.concurrent.Future;

@Remote
public interface CatalogRepositoryRemote {

    List<Catalog> findAll();
    Future<List<Catalog>> findAllAsync();
}
