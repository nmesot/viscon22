package ch.ipt.kafka.clients;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(properties = "INITIALS=test")
class ClientsApplicationTests {

    @Test
    void contextLoads() {
        assertTrue(true);
    }

}