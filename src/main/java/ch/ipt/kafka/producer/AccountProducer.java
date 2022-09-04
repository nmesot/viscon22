package ch.ipt.kafka.producer;

import ch.ipt.kafka.techbier.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;

//This class is only needed to produce data. probably someone else is already producing data
//@Configuration
public class AccountProducer {

    @Value("${source-topic-accounts}")
    private String sourceTopic;
    KafkaProducer kafkaProducer;

    public AccountProducer(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostConstruct
    public void sendAccounts() {
        Arrays.asList(AccountDataEnum.values())
                .forEach(
                        accountEnum ->  {
                            Account account = AccountDataEnum.getAccount(accountEnum);
                            kafkaProducer.sendAccount(account, sourceTopic);
                });
    }

}
