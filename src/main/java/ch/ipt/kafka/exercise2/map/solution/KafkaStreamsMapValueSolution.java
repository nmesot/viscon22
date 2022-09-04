package ch.ipt.kafka.exercise2.map.solution;

import ch.ipt.kafka.techbier.Payment;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


//@Component
public class KafkaStreamsMapValueSolution {

    @Value("${source-topic-transactions}")
    private String sourceTopic;

    @Value("${INITIALS}")
    private String initial;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaStreamsMapValueSolution.class);

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        String sinkTopic = "rounded-transactions-" + initial;

        //round up every amount to the next whole number (e.g. 12.20 --> 13.00)

        KStream<String, Payment> stream = streamsBuilder.stream(sourceTopic);

        stream.mapValues(value -> {
                    value.setAmount(Math.ceil(value.getAmount()));
                    return value;
                })
                .peek((key, payment) -> LOGGER.info("Message: key={}, value={}", key, payment))
                .to(sinkTopic);

        LOGGER.info(String.valueOf(streamsBuilder.build().describe()));
    }

}