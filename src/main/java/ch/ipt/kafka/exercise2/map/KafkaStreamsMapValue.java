package ch.ipt.kafka.exercise2.map;

import ch.ipt.kafka.techbier.Payment;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


//@Component
public class KafkaStreamsMapValue {

    @Value("${source-topic-transactions}")
    private String sourceTopic;

    @Value("${INITIALS}")
    private String initial;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaStreamsMapValue.class);

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        String sinkTopic = "rounded-transactions-" + initial;

        //round up every amount to the next whole number (e.g. 12.20 --> 13.00)

        KStream<String, Payment> stream = streamsBuilder.stream(sourceTopic);
        //TODO...

        LOGGER.info(String.valueOf(streamsBuilder.build().describe()));
    }

}