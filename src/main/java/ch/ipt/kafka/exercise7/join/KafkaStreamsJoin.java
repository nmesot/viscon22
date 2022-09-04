package ch.ipt.kafka.exercise7.join;

import ch.ipt.kafka.techbier.Account;
import ch.ipt.kafka.techbier.AccountPayment;
import ch.ipt.kafka.techbier.Payment;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


//@Component
public class KafkaStreamsJoin {

    @Value("${source-topic-transactions}")
    private String sourceTransactions;
    @Value("${source-topic-accounts}")
    private String sourceAccounts;

    @Value("${INITIALS}")
    private String initial;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaStreamsJoin.class);

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        String sinkTopic = "filtered-join-" + initial;

        // filter all Payments for the customers with last name "Fischer"
        // Hint look up fk-join

        KStream<String, Payment> transactionStream = streamsBuilder.stream(sourceTransactions);
        KStream<String, Account> accountStream = streamsBuilder.stream(sourceAccounts);

        //TODO

        LOGGER.info(String.valueOf(streamsBuilder.build().describe()));
    }

    private AccountPayment joinAccountTransaction(Payment payment, Account account) {
        //Just a little helper function... It might come in handy
        return new AccountPayment(account.getAccountId(),
                account.getSurname(),
                account.getLastname(),
                account.getStreet(),
                account.getCity(),
                payment.getId(),
                payment.getCardNumber(),
                payment.getCardType(),
                payment.getAmount());
    }

}