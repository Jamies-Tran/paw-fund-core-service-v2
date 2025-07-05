package com.paw.fund.core.service.domain.media;

import lombok.NonNull;

import java.util.List;

public interface IMediaUseCase {
    void saveAll(@NonNull MediaEventListener eventListener);

    List<Media> findAllByAccountId(@NonNull Long accountId);
}
