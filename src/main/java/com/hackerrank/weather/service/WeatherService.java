package com.hackerrank.weather.service;

import com.hackerrank.weather.entity.WeatherEntity;
import com.hackerrank.weather.error.exception.NotFoundException;
import com.hackerrank.weather.mapper.WeatherMapper;
import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.model.WeatherFilterDto;
import com.hackerrank.weather.repository.WeatherRepository;
import com.hackerrank.weather.repository.WeatherSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherMapper weatherMapper;
    private final WeatherRepository weatherRepository;


    public Weather addNewWeatherDataRecord(final Weather newWeather) {
        final WeatherEntity entity = weatherMapper.toEntity(newWeather);
        return weatherMapper.toDto(weatherRepository.save(entity));
    }

    public List<Weather> getWeatherDataRecordsByFilter(final WeatherFilterDto filter) {
        return weatherRepository.findAll(new WeatherSpecification(filter))
                .stream()
                .map(weatherMapper::toDto)
                .collect(Collectors.toList());
    }

    public Weather getWeatherDataRecordById(final Integer id) {
        return weatherRepository.findById(id)
                .map(weatherMapper::toDto)
                .orElseThrow(() -> NotFoundException.of("Data not found with id {0}", id));
    }

}
