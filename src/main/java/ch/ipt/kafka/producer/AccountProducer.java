package ch.ipt.kafka.producer;

import ch.ipt.kafka.viscon.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import static ch.ipt.kafka.producer.AccountDataEnum.getRandomAccount;

@Configuration
public class AccountProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountProducer.class);
	private final KafkaTemplate<String, Account> kafkaTemplateAccount;
	@Value("${source-topic-account-avro}")
	private String accountAvroTopic;

	public AccountProducer(KafkaTemplate<String, Account> kafkaTemplateAccount) {
		this.kafkaTemplateAccount = kafkaTemplateAccount;
	}

	@Scheduled(fixedRate = 2000)
	public void sendAccounts() {
		// TODO: Exercise 3.2
	}

}
