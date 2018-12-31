package com.github.levry.imq.spring.autoconfigure;

import com.sun.messaging.ConnectionFactory; // NOSONAR
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JndiConnectionFactoryAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.JMSException;

/**
 * @author levry
 */
@Configuration
@AutoConfigureBefore(JmsAutoConfiguration.class)
@AutoConfigureAfter({ JndiConnectionFactoryAutoConfiguration.class })
@ConditionalOnClass({javax.jms.ConnectionFactory.class, ConnectionFactory.class})
@EnableConfigurationProperties(ImqProperties.class)
public class ImqAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(javax.jms.ConnectionFactory.class)
    public ConnectionFactory connectionFactory(ImqProperties properties,
                                              ObjectProvider<ImqConnectionFactoryCustomizer> factoryCustomizer) throws JMSException {
        return new ImqConnectionFactoryBuilder(properties, factoryCustomizer.getIfAvailable()).createFactory();
    }

}
