package ch.ipt.kafka.exercise8.joinaggregate.solution;

import org.apache.kafka.streams.StreamsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


//@Component
public class KafkaStreamsJoinAggregateSolution {

    @Value("${source-topic-transactions}")
    private String sourceTopic;

    @Value("${INITIALS}")
    private String initial;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaStreamsJoinAggregateSolution.class);

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        String sinkTopic = "average-of-transactions-" + initial;

        //Compute the total of all payments for every single customer and create a new schema containing the account information plus the sum of the transactions

        // e.g. Customer: x, name: Fischer, credit transaction: xxx chf, debit transaction: yyy chf
        //TODO...

        LOGGER.info(String.valueOf(streamsBuilder.build().describe()));
    }

}