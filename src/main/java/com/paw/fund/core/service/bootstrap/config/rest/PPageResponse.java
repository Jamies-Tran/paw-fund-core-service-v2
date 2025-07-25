package com.paw.fund.core.service.bootstrap.config.rest;

import lombok.Builder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record PPageResponse<T>(
        List<T> data,
        PPageMeta meta,
        String status,
        Boolean success,
        String errorCode,
        LocalDateTime responseAt,
        String message
) {
    @Builder
    private record PPageMeta(
            Integer current,
            Integer pageSize,
            Integer totalPages,
            Long totalElement
    ) {
        public static PPageMeta of(Page<?> pageRequest) {
            return PPageMeta.builder()
                    .current(pageRequest.getPageable().getPageNumber())
                    .pageSize(pageRequest.getPageable().getPageSize())
                    .totalElement(pageRequest.getTotalElements())
                    .totalPages(pageRequest.getTotalPages())
                    .build();
        }
    }

    public static <T> PPageResponse<T> success(Page<T> page) {
        return PPageResponse.<T>builder()
                .data(page.getContent())
                .meta(PPageMeta.of(page))
                .status(String.valueOf(HttpStatus.OK.value()))
                .success(true)
                .message("Dịch vụ đã được thực hiện")
                .responseAt(LocalDateTime.now())
                .build();
    }
}
