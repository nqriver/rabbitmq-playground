package pl.nqriver.rabbitmq.producer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;

@Service
public class MeasurementProducer {

    private final RabbitMQProducer rabbitMQProducer;

    private static final Random RANDOM = new Random();

    public MeasurementProducer(RabbitMQProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @Scheduled(fixedDelay = 3000L)
    public void measure() {
        Measurement measurement = new Measurement(RANDOM.nextDouble(0., 23.),
                RANDOM.nextDouble(),
                RANDOM.nextDouble(),
                RANDOM.nextDouble(),
                "location",
                Instant.now());
        rabbitMQProducer.sendMessage(measurement);
    }
}
