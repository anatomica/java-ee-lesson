import store.Catalog;
import store.CatalogRepositoryRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;

public class EjbClient {

    public static void main(String[] args) throws Exception {
        Context context = createInitialContext();

        CatalogRepositoryRemote repositoryRemote = (CatalogRepositoryRemote) context.lookup("ejb:/base-jsf-app/CatalogRepositoryImpl!store.CatalogRepositoryRemote");
        repositoryRemote.findAll().forEach(prod -> System.out.println(prod.getName()));
        Future<List<Catalog>> future = repositoryRemote.findAllAsync();
        System.out.println("Waiting for task completion");
        future.get().forEach(prod -> System.out.println(prod.getName()));
    }

    public static Context createInitialContext() throws IOException, NamingException {
        final Properties env = new Properties();
        env.load(EjbClient.class.getClassLoader().getResourceAsStream("wildfly-jndi.properties"));
        return new InitialContext(env);
    }
}
