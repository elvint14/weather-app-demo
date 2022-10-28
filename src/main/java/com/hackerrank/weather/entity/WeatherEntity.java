package com.hackerrank.weather.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "weather")
public class WeatherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "latitude")
    private Float lat;

    @Column(name = "longitude")
    private Float lon;

    @ElementCollection
    @Column(name = "temperatures")
    private List<Double> temperatures = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherEntity that = (WeatherEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(lat, that.lat) && Objects.equals(lon, that.lon) && Objects.equals(city, that.city) && Objects.equals(state, that.state) && Objects.equals(temperatures, that.temperatures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, lat, lon, city, state, temperatures);
    }

}
