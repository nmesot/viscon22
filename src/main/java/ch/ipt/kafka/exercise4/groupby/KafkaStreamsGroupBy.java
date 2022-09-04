package ch.ipt.kafka.exercise4.groupby;

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
public class KafkaStreamsGroupBy {

    @Value("${source-topic-transactions}")
    private String sourceTopic;

    @Value("${INITIALS}")
    private String initial;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaStreamsGroupBy.class);

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        String sinkTopic = "grouped-transactions-" + initial;

        //count the number of payments grouped by the cardtype (e.g. "Debit": 12, "Credit": 27)

        KStream<String, Payment> stream = streamsBuilder.stream(sourceTopic);

        // stream...
        // TODO...

        LOGGER.info(String.valueOf(streamsBuilder.build().describe()));
    }

}