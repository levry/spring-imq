package imq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"imq.host=localhost"})
public class ImqApplicationTests {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Test
	public void sendMessage() {
		jmsTemplate.send("test.queue", session -> session.createTextMessage("Hello from levry"));
	}

}
