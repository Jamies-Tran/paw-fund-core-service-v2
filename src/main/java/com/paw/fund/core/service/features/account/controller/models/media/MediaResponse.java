package com.paw.fund.core.service.features.account.controller.models.media;

public record MediaResponse(
        Long mediaId,
        String url,

        String mediaTypeCode,
        String mediaTypeName
) {
}
