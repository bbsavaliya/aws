package com.aws.sqs.awssqseventdriven.service;


import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.aws.sqs.awssqseventdriven.configuration.QueueConfiguration;
import com.aws.sqs.awssqseventdriven.eventmessage.EventMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.messaging.core.QueueMessageChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
public class MessageSender {
    private static final Logger logger = LoggerFactory.getLogger(MessageSender.class);

    @Autowired
    private QueueConfiguration queueConfig;

    public boolean send(final EventMessage eventMessage) {
        final ObjectMapper objectMapper = new ObjectMapper();
        String jsonMessage = null;

        try {
            jsonMessage = objectMapper.writeValueAsString(eventMessage);
        } catch (JsonProcessingException e) {
            logger.error("getting error while converting event message to json format: Reason: " + e.getMessage());
        }

        SendMessageRequest msg =  queueConfig.sendMessageFifoQueue().withQueueUrl(queueConfig.fifoQueueURL())
                                    .withMessageBody(jsonMessage)
                                    .withMessageGroupId("docx-ref-group-1");

        queueConfig.amazonSQS().sendMessage(msg);
        logger.info("message sent");
        return true;
    }
}