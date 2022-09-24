package ch.ipt.kafka.consumer;


import ch.ipt.kafka.techbier.Account;
import ch.ipt.kafka.techbier.Payment;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class KafkaConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

	// TODO: Exercise 1
	@KafkaListener(id = "transactions-simple-consumer", topics = "transactions-simple")
	public void reviceSimpleMessage(ConsumerRecord<String, String> consumerRecord) {
		String key = consumerRecord.key();
		String value = consumerRecord.value();
		LOGGER.info("received credit message: key={}, value={}", key, value);
	}

	// TODO: Exercise 2
	@KafkaListener(id = "transactions-avro-consumer", topics = "transactions-avro")
	public void receiveTransactionMessage(ConsumerRecord<String, Payment> consumerRecord) {
		String key = consumerRecord.key();
		Payment value = consumerRecord.value();
		LOGGER.info("received credit message: key={}, value={}", key, value);
	}

	// TODO: Exercise 4
	@KafkaListener(id = "accounts-consumer", topics = "accounts-avro-NME")
	public void receiveAccountMessage(ConsumerRecord<String, Account> consumerRecord) {
		String key = consumerRecord.key();
		Account value = consumerRecord.value();
		LOGGER.info("received credit message: key={}, value={}", key, value);
	}
}
