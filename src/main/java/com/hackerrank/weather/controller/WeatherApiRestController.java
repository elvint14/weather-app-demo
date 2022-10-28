package com.hackerrank.weather.controller;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.model.WeatherFilterDto;
import com.hackerrank.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weather")
public class WeatherApiRestController {

    private final WeatherService weatherService;

    @PostMapping
    public ResponseEntity<Weather> addNewWeatherDataRecord(@Valid @RequestBody Weather weather) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(weatherService.addNewWeatherDataRecord(weather));
    }

    @GetMapping
    public ResponseEntity<List<Weather>> getWeatherDataRecordsByFilter(WeatherFilterDto weatherFilter) {
        return ResponseEntity.ok(weatherService.getWeatherDataRecordsByFilter(weatherFilter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Weather> getWeatherDataRecordById(@NotNull @PathVariable Integer id) {
        return ResponseEntity.ok(weatherService.getWeatherDataRecordById(id));
    }

}

