package com.paw.fund.core.service.domain.media;

import lombok.With;

public record Media(
        Long mediaId,
        @With Long accountId,
        Long reviewId,
        Long petId,
        Long shelterId,
        String url,

        String mediaTypeCode,
        String mediaTypeName
) {
}
