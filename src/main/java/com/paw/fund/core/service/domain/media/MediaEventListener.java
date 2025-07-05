package com.paw.fund.core.service.domain.media;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class MediaEventListener extends ApplicationEvent {
    Long accountId;
    List<Media> medias;

    public MediaEventListener(Object source, Long accountId, List<Media> medias) {
        super(source);
        this.accountId = accountId;
        this.medias = medias;
    }
}
