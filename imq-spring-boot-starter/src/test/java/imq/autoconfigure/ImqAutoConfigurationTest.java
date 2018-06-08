package imq.autoconfigure;

import com.sun.messaging.ConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import java.util.Properties;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author levry
 */
class ImqAutoConfigurationTest {

    private AnnotationConfigApplicationContext context;

    @AfterEach
    void tearDown() {
        if(null != context) {
            context.close();
        }
    }

    @Test
    void connectionFactory() {
        context = createApplicationContext(TestConfiguration.class, "imq.host=localhost");

        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        ConnectionFactory connectionFactory = context.getBean(ConnectionFactory.class);

        assertThat(jmsTemplate.getConnectionFactory()).isEqualTo(connectionFactory);
    }

    @DisplayName("Configure ConnectionFactory")
    @MethodSource("connectionFactoryParameters")
    @ParameterizedTest(name = "[{index}] environment=''{0}'', property: ''{1}'' is equal to ''{2}''")
    void configureConnectionFactory(String env, String propertyName, String propertyValue) {
        context = createApplicationContext(TestConfiguration.class, env);

        ConnectionFactory connectionFactory = context.getBean(ConnectionFactory.class);
        Properties config = connectionFactory.getConfiguration();

        assertThat(config.getProperty(propertyName)).isEqualTo(propertyValue);
    }

    static Stream<Arguments> connectionFactoryParameters() {
        return Stream.of(
                Arguments.of("imq.host=localhost", "imqBrokerHostName", "localhost"),
                Arguments.of("imq.port=17676", "imqBrokerHostPort", "17676"),
                Arguments.of("imq.username=tester", "imqDefaultUsername", "tester"),
                Arguments.of("imq.password=secret", "imqDefaultPassword", "secret")
        );
    }

    private AnnotationConfigApplicationContext createApplicationContext(Class<?> config, String... env) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        TestPropertyValues.of(env).applyTo(context);
        context.register(config);
        context.register(ImqAutoConfiguration.class, JmsAutoConfiguration.class);
        context.refresh();
        return context;
    }

    @Configuration
    static class TestConfiguration {

    }

}