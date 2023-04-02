package pl.nqriver.rabbit.consumer;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MongoTemplate mongoTemplate;

    public MeasurementController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @GetMapping
    public List<MeasurementDocument> getAllMeasurements() {
        return mongoTemplate.findAll(MeasurementDocument.class);
    }

    @GetMapping("/{id}")
    public MeasurementDocument getMeasurementById(@PathVariable String id) {
        return mongoTemplate.findById(id, MeasurementDocument.class);
    }

    @PostMapping
    public MeasurementDocument createMeasurement(@RequestBody MeasurementDocument measurementDocument) {
        return mongoTemplate.save(measurementDocument);
    }

    @PutMapping("/{id}")
    public MeasurementDocument updateMeasurement(@PathVariable String id, @RequestBody MeasurementDocument measurementDocument) {
        MeasurementDocument existingMeasurement = mongoTemplate.findById(id, MeasurementDocument.class);
        if (existingMeasurement == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Measurement not found");
        }
        measurementDocument.setId(id);
        return mongoTemplate.save(measurementDocument);
    }

    @DeleteMapping("/{id}")
    public void deleteMeasurement(@PathVariable String id) {
        mongoTemplate.remove(Query.query(Criteria.where("_id").is(id)), MeasurementDocument.class);
    }

    @DeleteMapping
    public void deleteAll() {
        mongoTemplate.remove(Query.query(new Criteria()), MeasurementDocument.class);
    }

}