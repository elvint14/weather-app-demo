package com.hackerrank.weather.repository;

import com.hackerrank.weather.entity.WeatherEntity;
import com.hackerrank.weather.entity.WeatherEntity_;
import com.hackerrank.weather.model.WeatherFilterDto;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
public class WeatherSpecification implements Specification<WeatherEntity> {

    private WeatherFilterDto filter;

    @Override
    public Predicate toPredicate(Root<WeatherEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(filter.getDate())) {
            predicates.add(criteriaBuilder.equal(root.get(WeatherEntity_.DATE), filter.getDate()));
        }

        if (Objects.nonNull(filter.getCity())) {
            filter.setCity(filter.getCity()
                    .stream()
                    .map(String::toLowerCase)
                    .collect(Collectors.toList()));
            predicates.add(criteriaBuilder.lower(root.get(WeatherEntity_.CITY))
                    .in(filter.getCity()));
        }

        if (Objects.equals(filter.getSort(), "date")) {
            query.orderBy(criteriaBuilder.asc(root.get(WeatherEntity_.DATE)),
                    criteriaBuilder.asc(root.get(WeatherEntity_.ID)));
        } else if (Objects.equals(filter.getSort(), "-date")) {
            query.orderBy(criteriaBuilder.desc(root.get(WeatherEntity_.DATE)),
                    criteriaBuilder.asc(root.get(WeatherEntity_.ID)));
        } else {
            query.orderBy(criteriaBuilder.asc(root.get(WeatherEntity_.ID)));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

}
