package ch.ipt.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;


@Component
public class KafkaDefaultTopology {

	//TODO: Exercise 3
	@Bean
	public NewTopic account() {
		return TopicBuilder.name("accounts-avro-NME").partitions(6).replicas(3).build();
	}
}