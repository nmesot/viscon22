package ch.ipt.kafka.config;

import ch.ipt.kafka.techbier.Payment;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;


@Component
/**
 * This class is only here to have a basic topology so the project is runable.
 */
public class KafkaStreamsDefaultTopology {

    @Value("${INITIALS}")
    private String initial;

    @Value("${source-topic-transactions}")
    private String sourceTopic;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaStreamsDefaultTopology.class);

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {

        //default topology which peeks at every transaction

        KStream<String, Payment> messageStream = streamsBuilder.stream(sourceTopic);

        messageStream.peek((key, payment) -> LOGGER.trace("Message: key={}, value={}", key, payment));

        LOGGER.info(String.valueOf(streamsBuilder.build().describe()));
    }

    @Bean
    public NewTopic topicExample1() {
        return TopicBuilder.name("filtered-transactions-" + initial)
                .partitions(6)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic topicExample2() {
        return TopicBuilder.name("filtered-transactions-" + initial)
                .partitions(6)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic topicExample3() {
        return TopicBuilder.name("rounded-transactions-" + initial)
                .partitions(6)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic topicExample4() {
        return TopicBuilder.name("credit-transactions-" + initial)
                .partitions(6)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic topicExample5() {
        return TopicBuilder.name("debit-transactions-" + initial)
                .partitions(6)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic topicExample6() {
        return TopicBuilder.name("undefined-transactions-" + initial)
                .partitions(6)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic topicExample7() {
        return TopicBuilder.name("grouped-transactions-" + initial)
                .partitions(6)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic topicExample8() {
        return TopicBuilder.name("total-of-transactions-" + initial)
                .partitions(6)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic topicExample9() {
        return TopicBuilder.name("transactions-last-minute-" + initial)
                .partitions(6)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic topicExample10() {
        return TopicBuilder.name("filtered-join-" + initial)
                .partitions(6)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic topicExample11() {
        return TopicBuilder.name("average-of-transactions-" + initial)
                .partitions(6)
                .replicas(3)
                .build();
    }

}