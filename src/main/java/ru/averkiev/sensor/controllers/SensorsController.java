package ru.averkiev.sensor.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.averkiev.sensor.dto.SensorDTO;
import ru.averkiev.sensor.models.Sensor;
import ru.averkiev.sensor.services.SensorService;
import ru.averkiev.sensor.util.MeasurementErrorResponse;
import ru.averkiev.sensor.util.MeasurementException;
import ru.averkiev.sensor.util.SensorValidator;

import static ru.averkiev.sensor.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorsController {

    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;


    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        Sensor sensorToAdd = convertToSensor(sensorDTO);

        sensorValidator.validate(sensorToAdd, bindingResult);
        if (bindingResult.hasErrors()) {
            returnErrorsToClient(bindingResult);
        }

        sensorService.register(sensorToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handlerException(MeasurementException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
