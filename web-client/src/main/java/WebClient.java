import store.Catalog;
import store.CatalogRepositoryWS;
import store.CatalogService;
import java.math.BigDecimal;
import java.net.URL;

public class WebClient {

    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:8080/base-jsf-app/CatalogService/CatalogRepositoryImpl?WSDL");
        CatalogService catalogService = new CatalogService(url);
        CatalogRepositoryWS catalogRepositoryImplPort = catalogService.getCatalogRepositoryImplPort();

        Catalog catalog1 = new Catalog();
        catalog1.setName("WS Product");
        catalog1.setDescription("Created from WS Client");
        catalog1.setPrice(BigDecimal.valueOf(50.00));

        Catalog catalog2 = new Catalog();
        catalog2.setName("Updated WS Product");
        catalog2.setId(16L);
        catalog2.setDescription("Created from WS Client");
        catalog2.setPrice(BigDecimal.valueOf(50.00));

        // catalogRepositoryImplPort.insert(catalog1);
        catalogRepositoryImplPort.update(catalog2);
        // catalogRepositoryImplPort.delete(15L);

        catalogRepositoryImplPort.findAll()
                .forEach(p -> System.out.println(p.getName() + " " +
                        p.getDescription() + " " + p.getPrice()));
    }
}
