package com.paw.fund.core.service.domain.media.info;

import com.paw.fund.core.service.enums.media.EMimeType;
import lombok.Builder;

import java.util.List;

@Builder
public record MediaInfoList(
        List<MediaInfo> infos
) {
    public record MediaInfo(
            String url,
            String mediaTypeCode,
            String mediaTypeName
    ) {
        public MediaInfo {
            EMimeType mimeType = EMimeType.INSTANCE.findMimeType(url);
            mediaTypeCode = mimeType.getCode();
            mediaTypeName = mimeType.getName();
        }
    }
}
