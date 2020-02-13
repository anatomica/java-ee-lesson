package jms;

import store.Catalog;
import store.CatalogRepository;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/*
Перед разворачиванием приложения необходимо создать очередь на сервере приложений
Это делается приведенной ниже командой через консоль jboss-cli.sh

jms-queue add --queue-address= jms.queue.ProductQueue --entries=java:jms/queue/productQueue,java:jboss/exported/jms/queue/productQueue
*/

//@MessageDriven(activationConfig = {
//        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
//        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jboss/exported/jms/queue/productQueue"),
//        @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "action = 'create'")
//})
//public class JmsProductBean implements MessageListener {
//
//    @EJB
//    private CatalogRepository catalogRepository;
//
//    @Override
//    public void onMessage(Message message) {
//        if (message instanceof ObjectMessage) {
//            try {
//                Catalog product = (Catalog) ((ObjectMessage) message).getObject();
//                catalogRepository.insert(product);
//            } catch (JMSException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}