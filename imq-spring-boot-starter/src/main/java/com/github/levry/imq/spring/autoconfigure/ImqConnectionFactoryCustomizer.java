package com.github.levry.imq.spring.autoconfigure;

import com.sun.messaging.ConnectionFactory; // NOSONAR

import javax.jms.JMSException;

/**
 * @author levry
 */
@FunctionalInterface
public interface ImqConnectionFactoryCustomizer {

    void customize(ConnectionFactory connectionFactory) throws JMSException;

}
