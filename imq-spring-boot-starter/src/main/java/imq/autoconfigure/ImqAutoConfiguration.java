package imq.autoconfigure;

import com.sun.messaging.ConnectionFactory;
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
    public ConnectionFactory containerFactory(ImqProperties properties) throws JMSException {
        return new ImqConnectionFactoryBuilder(properties).createFactory();
    }

}
