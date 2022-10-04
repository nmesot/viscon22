package ch.ipt.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;


//@Component // Uncomment this annotation if you want to test out the solution
public class KafkaTopologySolution {

	@Bean
	public NewTopic account() {
		return TopicBuilder.name("accounts-avro-NME").partitions(6).replicas(3).build();
	}
}