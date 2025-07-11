package com.paw.fund.core.service.features.media.service;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import com.paw.fund.core.service.domain.media.Media;
import com.paw.fund.core.service.features.media.repository.database.IMediaMapper;
import com.paw.fund.core.service.features.media.repository.database.IMediaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MediaCommandService {
    IMediaRepository repository;

    IMediaMapper mapper;

    void saveAll(Long accountId, List<Media> medias) {
        List<Media> medias2 = PObjectUtils.defaultList(medias)
                .stream()
                .map(media -> media.withAccountId(accountId))
                .toList();

        repository.saveAll(mapper.toEntity(medias2));
    }
}
