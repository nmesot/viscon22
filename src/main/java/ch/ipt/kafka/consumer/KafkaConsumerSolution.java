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


//@Component // Uncomment this annotation if you want to test out the solution
public class KafkaConsumerSolution implements ConsumerSeekAware {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerSolution.class);
	private static final String INITIALS = "SOLUTION";
	private double totalEarned = 0;

	@KafkaListener(id = "transactions-simple-consumer-" + INITIALS, topics = "transactions-simple")
	public void reviceSimpleMessage(ConsumerRecord<String, String> consumerRecord) {
		String key = consumerRecord.key();
		String value = consumerRecord.value();
		LOGGER.info("received credit message: key={}, value={}", key, value);
	}

	@KafkaListener(id = "transactions-avro-consumer-" + INITIALS, topics = "transactions-avro")
	public void receiveTransactionMessage(ConsumerRecord<String, Payment> consumerRecord) {
		String key = consumerRecord.key();
		Payment value = consumerRecord.value();
		totalEarned += value.getAmount();
		LOGGER.info("received credit message: key={}, value={}. Total earned so far: {}", key, value, totalEarned);
	}

	@KafkaListener(id = "accounts-consumer", topics = "accounts-avro-NME")
	public void receiveAccountMessage(ConsumerRecord<String, Account> consumerRecord) {
		String key = consumerRecord.key();
		Account value = consumerRecord.value();
		LOGGER.info("received credit message: key={}, value={}", key, value);
	}

	@Override
	public void registerSeekCallback(ConsumerSeekCallback callback) {

	}

	@Override
	public void onPartitionsAssigned(Map<TopicPartition, Long> assignments, ConsumerSeekCallback callback) {
		assignments.forEach((t, o) -> callback.seekToEnd(t.topic(), t.partition()));
	}

	@Override
	public void onIdleContainer(Map<TopicPartition, Long> assignments, ConsumerSeekCallback callback) {


	}
}
