package ch.ipt.kafka.exercise5.aggregate;

import ch.ipt.kafka.techbier.Payment;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


//@Component
public class KafkaStreamsAggregate {

    @Value("${source-topic-transactions}")
    private String sourceTopic;

    @Value("${INITIALS}")
    private String initial;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaStreamsAggregate.class);

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        String sinkTopic = "total-of-transactions-" + initial;

        //compute the total of all transactions per account (e.g. account x : 1632.45, account y: 256.00, ...)
        KStream<String, Payment> groupedStream = streamsBuilder.stream(sourceTopic);

        //TODO...

        LOGGER.info(String.valueOf(streamsBuilder.build().describe()));
    }

}