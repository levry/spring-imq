package imq.autoconfigure;

import com.sun.messaging.ConnectionFactory;

import javax.jms.JMSException;
import java.util.function.Supplier;

import static com.sun.messaging.ConnectionConfiguration.*;

/**
 * @author levry
 */
public class ImqConnectionFactoryBuilder {

    private final ImqProperties properties;

    public ImqConnectionFactoryBuilder(ImqProperties properties) {
        this.properties = properties;
    }

    public ConnectionFactory createFactory() throws JMSException {
        ConnectionFactory cf = new ConnectionFactory();
        setProperty(cf, imqBrokerHostName, properties::getHost);
        setProperty(cf, imqBrokerHostPort, properties::getPort);
        setProperty(cf, imqDefaultUsername, properties::getUsername);
        setProperty(cf, imqDefaultPassword, properties::getPassword);
        return cf;
    }

    private void setProperty(ConnectionFactory cf, String name, Supplier<?> param) throws JMSException {
        Object value = param.get();
        if (value != null) {
            cf.setProperty(name, String.valueOf(value));
        }
    }

}
