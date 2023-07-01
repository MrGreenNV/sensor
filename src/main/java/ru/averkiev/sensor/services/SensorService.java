package ru.averkiev.sensor.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.averkiev.sensor.models.Sensor;
import ru.averkiev.sensor.repositories.SensorRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;

    public Optional<Sensor> findByName(String name) {
        return sensorRepository.findByName(name);
    }

    public void register(Sensor sensor) {
        sensorRepository.save(sensor);
    }
}
