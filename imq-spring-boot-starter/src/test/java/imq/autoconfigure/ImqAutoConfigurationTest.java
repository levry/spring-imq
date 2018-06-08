package imq.autoconfigure;

import com.sun.messaging.ConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

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