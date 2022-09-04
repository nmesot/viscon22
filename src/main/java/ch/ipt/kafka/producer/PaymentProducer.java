package ch.ipt.kafka.producer;

import ch.ipt.kafka.techbier.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.UUID;

//This class is only needed to produce data. probably someone else is already producing data
//@Configuration
public class PaymentProducer {

    @Value("${source-topic-transactions}")
    private String sourceTopic;

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentProducer.class);
    KafkaProducer kafkaProducer;

    public PaymentProducer(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @Scheduled(fixedRate = 2000)
    public void scheduleFixedRateTask() {
        double amount = generateDouble();
        String id = UUID.randomUUID().toString();
        AccountDataEnum account = AccountDataEnum.getRandomEnum();
        Payment payment = new Payment(id, account.getAccountId(), account.getPan(), account.getCardType(), amount);
        LOGGER.debug("Payment created with values: {}, {}", payment.getId(), payment.getAmount());
        kafkaProducer.sendPayment(payment, sourceTopic);
    }

    private Double generateDouble(){
        Double min = 0.0;
        Double max = 1000.0;
        double x = (Math.random() * ((max - min) + 1)) + min;   // This Will Create A Random Number in between Min And Max.
        return Math.round(x * 100.0) / 100.0;
    }


}
