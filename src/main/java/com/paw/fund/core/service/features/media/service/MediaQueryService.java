package com.paw.fund.core.service.features.media.service;

import com.paw.fund.core.service.domain.media.Media;
import com.paw.fund.core.service.features.media.repository.database.IMediaMapper;
import com.paw.fund.core.service.features.media.repository.database.IMediaRepository;
import com.paw.fund.core.service.features.media.repository.database.MediaEntity;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MediaQueryService {
    IMediaRepository repository;

    IMediaMapper mapper;

    protected List<Media> findAllByAccountId(@NonNull Long accountId) {
        List<MediaEntity> medias = repository.findAllByAccountId(accountId);

        return mapper.toDto(medias);
    }
}
