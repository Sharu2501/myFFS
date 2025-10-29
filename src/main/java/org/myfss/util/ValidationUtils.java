package org.myfss.util;

import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.Function;

public class ValidationUtils {

    public static <E extends RuntimeException> void validateRequiredFields(Map<String, Object> fields, Function<String, E> exceptionSupplier) {
        fields.forEach((fieldName, value) -> {
            if (value == null || (value instanceof String && ((String) value).trim().isEmpty())) {
                throw exceptionSupplier.apply("Le champ " + fieldName + " est obligatoire.");
            }
        });
    }


    public static void checkConflicts(Map<String, BooleanSupplier> conflicts, Function<String, RuntimeException> exceptionSupplier) {
        conflicts.forEach((fieldMessage, existsSupplier) -> {
            if (existsSupplier.getAsBoolean()) {
                throw exceptionSupplier.apply(fieldMessage);
            }
        });
    }
}
