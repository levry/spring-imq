package imq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private static final Logger logger = LoggerFactory.getLogger(ImqApplication.class);

    @JmsListener(destination = "test.imq")
    void receiveMessage(Message incoming) {
        logger.warn("Incoming message: {}", incoming);
    }

}
