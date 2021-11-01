package com.aws.sqs.awssqseventdriven;

import com.aws.sqs.awssqseventdriven.eventmessage.EventMessage;
import com.aws.sqs.awssqseventdriven.eventmessage.EventMetadata;
import com.aws.sqs.awssqseventdriven.eventmessage.ReferralCreateEvent;
import com.aws.sqs.awssqseventdriven.service.MessageSender;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class AwsSqsEventDrivenApplication implements CommandLineRunner {
	@Autowired
	private MessageSender messageSender;

	public static void main(String[] args) {
		SpringApplication.run(AwsSqsEventDrivenApplication.class, args);
	}

	@Override
	public void run(String... args) {
		EventMessage<ReferralCreateEvent> eventMessage =  this.initializeEvent();
		boolean messageStatus = this.messageSender.send(eventMessage);
		System.out.println("Message sent Status: " + messageStatus);
	}

	private EventMessage<ReferralCreateEvent> initializeEvent() {
		ReferralCreateEvent referralCreateEvent = new ReferralCreateEvent();
		referralCreateEvent.setReferralId(1l);
		referralCreateEvent.setFromDocId(10l);
		referralCreateEvent.setToDocId(20l);
		referralCreateEvent.setFromDocFirstName("testx");
		referralCreateEvent.setFromDocLastName("testy");
		referralCreateEvent.setToDocFirstName("testxx");
		referralCreateEvent.setToDocLastName("testyy");

		EventMetadata eventMetadata = new EventMetadata();
		eventMetadata.setId("docx-ref-event-1");
		eventMetadata.setCreatedAt(LocalDate.now().toString());
		eventMetadata.setCreatedAt(LocalDate.now().toString());

		Map<String, Object> messageMetadata = new HashMap<>();
		messageMetadata.put("testx","testy");

		EventMessage<ReferralCreateEvent> eventMessage = new EventMessage<>();
		eventMessage.setEvent(referralCreateEvent);
		eventMessage.setEventMetadata(eventMetadata);
		eventMessage.setMessageMetadata(messageMetadata);

		return eventMessage;
	}
}
