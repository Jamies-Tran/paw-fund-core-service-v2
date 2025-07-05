package com.paw.fund.core.service.bootstrap.config.mapper;

import java.util.List;

public interface IEntityMapper<E, D> {
    E toEntity(D d);

    D toDto(E e);

    List<E> toEntity(List<D> ds);

    List<D> toDto(List<E> es);
}
