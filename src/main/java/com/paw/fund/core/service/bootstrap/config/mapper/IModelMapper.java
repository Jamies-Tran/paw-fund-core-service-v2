package com.paw.fund.core.service.bootstrap.config.mapper;

import java.util.List;

public interface IModelMapper<M, D> {
    M toModel(D d);

    D toDto(M m);

    List<M> toModel(List<D> ds);

    List<D> toDto(List<M> ms);
}
