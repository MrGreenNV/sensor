package ru.averkiev.sensor.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.averkiev.sensor.dto.MeasurementResponse;
import ru.averkiev.sensor.models.Measurement;
import ru.averkiev.sensor.repositories.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    public void addMeasurement(Measurement measurement) {
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    private void enrichMeasurement(Measurement measurement) {
        measurement.setSensor(sensorService.findByName(measurement.getSensor().getName()).get());

        measurement.setMeasurementDateTime(LocalDateTime.now());
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }
}
