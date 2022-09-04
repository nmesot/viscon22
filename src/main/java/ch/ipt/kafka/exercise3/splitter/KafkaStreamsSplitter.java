package ch.ipt.kafka.exercise3.splitter;

import ch.ipt.kafka.techbier.Payment;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


//@Component
public class KafkaStreamsSplitter {

    @Value("${source-topic-transactions}")
    private String sourceTopic;

    @Value("${INITIALS}")
    private String initial;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaStreamsSplitter.class);

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        String creditSinkTopic = "credit-transactions-" + initial;
        String debitSinkTopic = "debit-transactions-" + initial;
        String undefinedSinkTopic = "undefined-transactions-" + initial;

        //split the topic in two different sink topics: one for debit payments (into debit-transactions) and one for credit transactions (credit-transactions).

        KStream<String, Payment> stream = streamsBuilder
                .stream("transactions");


        //TODO...

        LOGGER.info(String.valueOf(streamsBuilder.build().describe()));
    }

}