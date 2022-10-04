package ch.ipt.kafka.consumer;


import ch.ipt.kafka.viscon.Account;
import ch.ipt.kafka.viscon.Payment;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class KafkaConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
	private static final String INITIALS = "TODO"; // TODO: Exercise 1

	@KafkaListener(id = "transactions-simple-consumer-" + INITIALS, topics = "transactions-simple")
	public void reviceSimpleMessage(ConsumerRecord<String, String> consumerRecord) {
		// TODO: Exercise 1 - Don't forget to replace INITIALS with your initials
	}

	// TODO: Exercise 2

	// TODO: Exercise 3.3
}
