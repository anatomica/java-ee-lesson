import store.Catalog;
import javax.naming.Context;
import javax.naming.NamingException;
import java.io.IOException;
import java.math.BigDecimal;
import javax.jms.*;

public class JmsClient {

    public static void main(String[] args) throws IOException, NamingException {
        Context context = EjbClient.createInitialContext();

        ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
        JMSContext jmsContext = connectionFactory.createContext("jmsuser", "123");

        Destination destination = (Destination) context.lookup("jms/queue/productQueue");

        JMSProducer producer = jmsContext.createProducer();

        ObjectMessage message = jmsContext.createObjectMessage(new Catalog(null, "JmsProduct", "JMS Product", new BigDecimal(1111)));
        producer.setProperty("action", "create").send(destination, message);
    }
}
