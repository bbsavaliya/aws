package com.aws.sqs.awssqseventdriven.service;

import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @SqsListener("${sqs.aws.fifi.queue.name}" )
    public void processMessage(final String message){
        logger.info("Received message {}", message);
    }
}
