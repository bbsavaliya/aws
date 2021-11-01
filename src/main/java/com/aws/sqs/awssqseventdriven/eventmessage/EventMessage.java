package com.aws.sqs.awssqseventdriven.eventmessage;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class EventMessage<T> {
    @Autowired
    private T event;

    @Autowired
    private EventMetadata eventMetadata;

    private Map<String, Object> messageMetadata;

    public T getEvent() {
        return event;
    }

    public EventMetadata getEventMetadata() {
        return eventMetadata;
    }

    public Map<String, Object> getMessageMetadata() {
        return messageMetadata;
    }

    public void setEvent(T event) {
        this.event = event;
    }

    public void setEventMetadata(EventMetadata eventMetadata) {
        this.eventMetadata = eventMetadata;
    }

    public void setMessageMetadata(Map<String, Object> messageMetadata) {
        this.messageMetadata = messageMetadata;
    }
}
