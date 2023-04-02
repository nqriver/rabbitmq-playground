package pl.nqriver.rabbit.consumer;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "measurements")
public class MeasurementDocument {
    @Id
    private String id;
    private double temperature;
    private double humidity;
    private double pressure;
    private double windSpeed;
    private String location;
    private Instant timestamp;

    static MeasurementDocument fromDto(MeasurementDto dto) {
        return new MeasurementDocument(
                dto.temperature(),
                dto.humidity(),
                dto.pressure(),
                dto.windSpeed(),
                dto.location(),
                dto.timestamp());
    }

    public MeasurementDocument(double temperature, double humidity, double pressure, double windSpeed, String location, Instant timestamp) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.location = location;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
