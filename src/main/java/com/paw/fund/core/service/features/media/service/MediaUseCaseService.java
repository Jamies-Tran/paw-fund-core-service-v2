package com.paw.fund.core.service.features.media.service;

import com.paw.fund.core.service.domain.media.IMediaUseCase;
import com.paw.fund.core.service.domain.media.Media;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MediaUseCaseService implements IMediaUseCase {
    MediaCommandService commandService;

    MediaQueryService queryService;

    @Override
    @Transactional
    public void saveAll(@NonNull Long accountId, List<Media> medias) {
        commandService.saveAll(accountId, medias);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Media> findAllByAccountId(@NonNull Long accountId) {
        return queryService.findAllByAccountId(accountId);
    }
}
