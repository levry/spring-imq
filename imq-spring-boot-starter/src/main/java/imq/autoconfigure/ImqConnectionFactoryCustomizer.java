package imq.autoconfigure;

import com.sun.messaging.ConnectionFactory;

import javax.jms.JMSException;

/**
 * @author levry
 */
@FunctionalInterface
public interface ImqConnectionFactoryCustomizer {

    void customize(ConnectionFactory connectionFactory) throws JMSException;

}
