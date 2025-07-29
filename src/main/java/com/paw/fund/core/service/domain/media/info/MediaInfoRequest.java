package com.paw.fund.core.service.domain.media.info;

import java.util.List;

public record MediaInfoRequest(
        List<MediaInfo> infos
) {
    public record MediaInfo(
            String url
    ) {}
}
