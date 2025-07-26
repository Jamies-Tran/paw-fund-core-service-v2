package com.paw.fund.core.service.bootstrap.utils;

import ch.qos.logback.core.util.StringUtil;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PObjectUtils {

    static Integer timeRangeDistance;

    @Value("${paw.timeRange.distance}")
    public void setTimeRangeDistance(Integer timeRangeDistance) {
        PObjectUtils.timeRangeDistance = timeRangeDistance;
    }

    public static Boolean isNull(Object object) {
        return object == null;
    }

    public static Boolean isNotNull(Object object) {
        return object != null;
    }

    public static Boolean isEmpty(String objectStr) {
        return objectStr == "" || objectStr == null;
    }

    public static Boolean isEmptyList(List<?> list) {
        return list == null || list.isEmpty();
    }

    public static Boolean isNotEmptyList(List<?> list) {
        return list != null && !list.isEmpty();
    }

    public static Boolean isEqual(Object object1, Object object2) {
        return Objects.equals(object1, object2);
    }

    public static Boolean isNotEqual(Object object1, Object object2) {
        return !Objects.equals(object1, object2);
    }

    public static  <T>List<T> defaultList(List<T> list) {
        return Optional.ofNullable(list).orElse(new ArrayList<>());
    }

    public static String defaultString(String str) {
        if (!StringUtils.hasText(str)) {
            return "";
        }

        return str;
    }

    public static List<LocalDateTime> defaultTimeRange(List<LocalDateTime> timeRange) {
        if (Objects.isNull(timeRange) || timeRange.isEmpty()) {
            return List.of(LocalDateTime.now().minusDays(timeRangeDistance), LocalDateTime.now());
        }

        if (timeRange.size() == 1) {
            return List.of(timeRange.getFirst().minusDays(timeRangeDistance), timeRange.getFirst());
        }

        if (timeRange.size() > 2) {
            return List.of(timeRange.getFirst(), timeRange.get(1));
        }

        return timeRange;
    }
}
