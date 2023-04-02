package pl.nqriver.rabbitmq.producer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Random;

@RestController
public class MessageController {

    private final RabbitMQProducer rabbitMQProducer;

    private static final Random RANDOM = new Random();

    public MessageController(RabbitMQProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @PostMapping("messages")
    public ResponseEntity<?> publish(@RequestParam("location") String location) {
        Measurement measurement = new Measurement(RANDOM.nextDouble(0., 23.),
                RANDOM.nextDouble(),
                RANDOM.nextDouble(),
                RANDOM.nextDouble(),
                location,
                Instant.now());

        rabbitMQProducer.sendMessage(measurement);
        return ResponseEntity.ok().build();
    }

}
