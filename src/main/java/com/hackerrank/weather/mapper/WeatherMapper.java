package com.hackerrank.weather.mapper;

import com.hackerrank.weather.entity.WeatherEntity;
import com.hackerrank.weather.model.Weather;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface WeatherMapper extends EntityMapper<Weather, WeatherEntity> {
}
