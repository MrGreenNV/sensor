package ru.averkiev.sensor.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.averkiev.sensor.models.Measurement;
import ru.averkiev.sensor.services.SensorService;

@Component
@RequiredArgsConstructor
public class MeasurementValidator implements Validator {

    private final SensorService sensorService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;

        if (measurement.getSensor() == null) {
            return;
        }

        if (sensorService.findByName(measurement.getSensor().getName()).isEmpty()) {
            errors.rejectValue("sensor", "Нет зарегистрированного сенсора с таким именем!");
        }
    }
}
