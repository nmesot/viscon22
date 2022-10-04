package ch.ipt.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;


@Component
public class KafkaTopology {


	@Bean
	public NewTopic account() {
		//TODO: Exercise 3.1
		return null;
	}
}