package com.aws.sqs.awssqseventdriven.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class QueueConfiguration {
    @Value("${cred.aws.access.key}")
    private String awsCredAccessKey;

    @Value("${cred.aws.secret.key}")
    private String awsCredSecretKey;

    @Value("${sqs.aws.fifi.queue.name}")
    private String awsSqsFifoQueue;

    @Value("${sqs.aws.region}")
    private String region;

    @Bean
    public AWSCredentials awsCredentials() {
        return new BasicAWSCredentials(awsCredAccessKey, awsCredSecretKey);
    }

    @Bean
    public String fifoQueueURL() {
        return amazonSQS().createQueue(createRequestQueue()).getQueueUrl();
    }

    @Bean
    public AmazonSQSAsync amazonSQS() {
        return AmazonSQSAsyncClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials()))
                .withRegion(Regions.fromName(region))
                .build();
    }

    @Bean
    public CreateQueueRequest createRequestQueue() {
        final Map<String, String> queueAttributes = new HashMap<>();
        queueAttributes.put("FifoQueue", "true");
        queueAttributes.put("ContentBasedDeduplication", "true");
        return new CreateQueueRequest(awsSqsFifoQueue).withAttributes(queueAttributes);
    }

    @Bean
    public SendMessageRequest sendMessageFifoQueue() {
        return new SendMessageRequest();
    }
}