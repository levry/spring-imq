package com.github.levry.imq.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private static final Logger logger = LoggerFactory.getLogger(ImqApplication.class);

    @JmsListener(destination = "${imq.incoming:test.queue}")
    void receiveMessage(Message incoming) {
        logger.warn("Incoming message: payload={}, headers={}", incoming.getPayload(), incoming.getHeaders());
    }

}
