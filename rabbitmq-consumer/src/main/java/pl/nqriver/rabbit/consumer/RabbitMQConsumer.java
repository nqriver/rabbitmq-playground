package pl.nqriver.rabbit.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMQConsumer.class.getName());

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(Measurement measurement) {
        LOG.info("Consumer has received a message! {}", measurement.toString());
    }
}
