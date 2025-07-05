package com.paw.fund.core.service.bootstrap.utils;

import java.util.Objects;

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
}
