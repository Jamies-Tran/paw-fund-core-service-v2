package com.paw.fund.core.service.bootstrap.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PObjectUtils {
    public static Boolean isNull(Object object) {
        return object == null;
    }

    public static Boolean isEmpty(String objectStr) {
        return objectStr == "" || objectStr == null;
    }

    public static Boolean isEqual(Object object1, Object object2) {
        return Objects.equals(object1, object2);
    }

    public static Boolean isNotEqual(Object object1, Object object2) {
        return !Objects.equals(object1, object2);
    }

    public static  <T>List<T> defaultList(List<T> list ) {
        return Optional.ofNullable(list).orElse(new ArrayList<>());
    }
}
