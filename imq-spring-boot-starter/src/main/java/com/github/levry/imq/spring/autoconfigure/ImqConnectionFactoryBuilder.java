package com.github.levry.imq.spring.autoconfigure;

import com.sun.messaging.ConnectionFactory; // NOSONAR

import javax.jms.JMSException;
import java.util.function.Supplier;

import static com.sun.messaging.ConnectionConfiguration.*;

/**
 * @author levry
 */
class ImqConnectionFactoryBuilder {

    private final ImqProperties properties;
    private final ImqConnectionFactoryCustomizer factoryCustomizer;

    ImqConnectionFactoryBuilder(ImqProperties properties, ImqConnectionFactoryCustomizer factoryCustomizer) {
        this.properties = properties;
        this.factoryCustomizer = factoryCustomizer;
    }

    ConnectionFactory createFactory() throws JMSException {
        final ConnectionFactory cf = new ConnectionFactory();
        setupProperties(cf);
        customize(cf);
        return cf;
    }

    private void setupProperties(ConnectionFactory cf) throws JMSException {
        setProperty(cf, imqBrokerHostName, properties::getHost);
        setProperty(cf, imqBrokerHostPort, properties::getPort);
        setProperty(cf, imqDefaultUsername, properties::getUsername);
        setProperty(cf, imqDefaultPassword, properties::getPassword);
    }

    private void customize(ConnectionFactory cf) throws JMSException {
        if (null != factoryCustomizer) {
            factoryCustomizer.customize(cf);
        }
    }

    private void setProperty(ConnectionFactory cf, String name, Supplier<?> param) throws JMSException {
        Object value = param.get();
        if (value != null) {
            cf.setProperty(name, String.valueOf(value));
        }
    }

}
