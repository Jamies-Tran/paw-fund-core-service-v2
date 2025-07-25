package com.paw.fund.core.service.bootstrap.config.rest;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import org.springframework.data.domain.Sort;

public record PSorter() {
    public static Sort of(String sorter) {
        String[] sorters = sorter.split("_");
        Sort.Direction direction = null;

        if (sorters.length == 1) {
            direction = Sort.Direction.DESC;
        } else  if (PObjectUtils.isEqual("desc", sorters[1])) {
            direction = Sort.Direction.DESC;
        } else if(PObjectUtils.isEqual("asc", sorters[1])) {
            direction = Sort.Direction.ASC;
        } else {
            direction = Sort.Direction.DESC;
        }

        return Sort.by(direction, sorters[0]);
    }
}
