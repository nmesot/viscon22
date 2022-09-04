package ch.ipt.kafka.exercise4.groupby.solution;

import ch.ipt.kafka.techbier.Payment;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


//@Component
public class KafkaStreamsGroupBySolution {

    @Value("${source-topic-transactions}")
    private String sourceTopic;

    @Value("${INITIALS}")
    private String initial;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaStreamsGroupBySolution.class);

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        String sinkTopic = "grouped-transactions-" + initial;

        //count the number of payments grouped by the cardtype (e.g. "Debit": 12, "Credit": 27)

        KStream<String, Payment> stream = streamsBuilder.stream(sourceTopic);

        stream.map((key, value) -> new KeyValue<>(
                        value.getCardType().toString(), value
                ))
                .groupByKey()
                .count(Materialized.as("grouped-transactions-count"))
                .toStream()
                .peek((key, value) -> LOGGER.info("Grouped Transactions: key={}, value={}", key, value))
                .to(sinkTopic);

        LOGGER.info(String.valueOf(streamsBuilder.build().describe()));
    }

}
