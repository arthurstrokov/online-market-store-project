package com.gmail.arthurstrokov.service.converter;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public interface EntityConverter<E, D> {

    E toEntity(D dto);

    default List<E> toEntityList(List<D> list) {
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    default Set<E> toEntitySet(Set<D> dtoSet) {
        return dtoSet.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }
}