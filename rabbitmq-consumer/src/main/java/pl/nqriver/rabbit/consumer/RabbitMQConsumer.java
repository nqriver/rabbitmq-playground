package pl.nqriver.rabbit.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMQConsumer.class.getName());


    private final MongoTemplate mongoTemplate;

    public RabbitMQConsumer(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(MeasurementDto measurementDto) {
        MeasurementDocument measurement = MeasurementDocument.fromDto(measurementDto);
        mongoTemplate.save(measurement);
        LOG.info("Consumer has received a message! {}", measurementDto.toString());
    }
}
