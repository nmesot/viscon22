package ch.ipt.kafka.producer;

import ch.ipt.kafka.techbier.Account;
import ch.ipt.kafka.techbier.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    KafkaTemplate<String, Payment> kafkaTemplatePayment;
    @Autowired
    KafkaTemplate<String, Account> kafkaTemplateAccount;

    public void sendPayment(Payment message, String topic) {
        ListenableFuture<SendResult<String, Payment>> future =
                kafkaTemplatePayment.send(topic, message.getId().toString() , message);

        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, Payment> result) {
                LOGGER.info("Message [{}] delivered with offset {}",
                        message,
                        result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                LOGGER.warn("Unable to deliver message [{}]. {}",
                        message,
                        ex.getMessage());
            }
        });
    }

    public void sendAccount(Account message, String topic) {
        ListenableFuture<SendResult<String, Account>> future =
                kafkaTemplateAccount.send(topic, message.getAccountId().toString() , message);

        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, Account> result) {
                LOGGER.info("Message [{}] delivered with offset {}",
                        message,
                        result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                LOGGER.warn("Unable to deliver message [{}]. {}",
                        message,
                        ex.getMessage());
            }
        });
    }
}
