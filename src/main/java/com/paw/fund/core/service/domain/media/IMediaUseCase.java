package com.paw.fund.core.service.domain.media;

import lombok.NonNull;

import java.util.List;

public interface IMediaUseCase {
    void saveAll(@NonNull Long accountId, List<Media> medias);

    List<Media> findAllByAccountId(@NonNull Long accountId);
}
