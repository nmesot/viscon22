package ch.ipt.kafka.exercise5.aggregate.solution;

import ch.ipt.kafka.techbier.Payment;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


//@Component
public class KafkaStreamsAggregateSolution {

    @Value("${source-topic-transactions}")
    private String sourceTopic;

    @Value("${INITIALS}")
    private String initial;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaStreamsAggregateSolution.class);

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        String sinkTopic = "total-of-transactions-" + initial;

        //compute the total of all transactions per account (e.g. account x : 1632.45, account y: 256.00, ...)

        KStream<String, Payment> groupedStream = streamsBuilder.stream(sourceTopic);

        groupedStream.map((key, value) -> new KeyValue<>(value.getAccountId().toString(), value))
                .groupByKey()
                .aggregate(
                        () -> 0.0, // Initial Value
                        (key, payment, total) -> total + payment.getAmount(),
                        Materialized.as("total-of-transactions-aggregate")
                )
                .toStream()
                .peek((key, value) -> LOGGER.info("Total of transactions: key={}, value={}", key, value));

        groupedStream.to(sinkTopic);

        LOGGER.info(String.valueOf(streamsBuilder.build().describe()));
    }

}