package ru.averkiev.sensor.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "measurement")
@Getter
@Setter
@RequiredArgsConstructor
public class Measurement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    @NotNull
    @Min(-100)
    @Max(100)
    private Double value;

    @Column(name = "raining")
    @NotNull
    private Boolean isRaining;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sensor", referencedColumnName = "name")
    private Sensor sensor;
}
