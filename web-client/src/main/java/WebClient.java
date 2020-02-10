import store.CatalogRepositoryWS;
import store.CatalogService;

import java.net.URL;

public class WebClient {

    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:8080/base-jsf-app/CatalogService/CatalogRepositoryImpl?WSDL");
        CatalogService catalogService = new CatalogService(url);
        CatalogRepositoryWS catalogRepositoryImplPort = catalogService.getCatalogRepositoryImplPort();

        catalogRepositoryImplPort.findAll()
                .forEach(p -> System.out.println(p.getName()));

    }
}
