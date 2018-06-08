package imq;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.UUID;

import static java.time.Duration.ofSeconds;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTimeout;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {"imq.host=localhost"})
class ImqApplicationTests {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Test
	void sendMessage() {
		jmsTemplate.send("test.queue", session -> session.createTextMessage("Hello from levry"));
	}

	@Test
	void sendAndReceiveMessage() throws Exception {
		String messageId = UUID.randomUUID().toString();

		jmsTemplate.send("test.imq", session -> {
			TextMessage message = session.createTextMessage("Hello from levry");
			message.setJMSCorrelationID(messageId);
			return message;
		});

		Message message = assertTimeout(ofSeconds(2), () -> {
			String messageSelector = "JMSCorrelationID = '" + messageId + "'";
			return jmsTemplate.receiveSelected("test.imq", messageSelector);
		});

		assertThat(message).isNotNull();
		assertThat(message).isInstanceOf(TextMessage.class);

		TextMessage textMessage = (TextMessage) message;
		assertThat(textMessage.getText()).isEqualTo("Hello from levry");
	}
}
