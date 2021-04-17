package com.creator.cloud.stream.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.KafkaNull;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.creator.cloud.stream.api.model.Book;

@SpringBootApplication
@EnableBinding(Source.class)
@RestController
public class SpringCloudStreamKafkaPublisherApplication {
	
	@Autowired
	private MessageChannel output;
	
	@PostMapping("/publish")
	public Book publishEvent(@RequestBody Book book) {
		System.out.println("inside publishEvent....");
		output.send(MessageBuilder.withPayload(book)
	            .build());
		return book;
	}
	


	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStreamKafkaPublisherApplication.class, args);
	}

}
