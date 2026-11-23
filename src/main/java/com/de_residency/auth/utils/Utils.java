package com.de_residency.auth.utils;

public final class Utils {
    private Utils() {}

    public static int getPositiveInt(String variable) {
        String value = System.getenv(variable);

        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                    "Environment variable " + variable + " is not configured"
            );
        }

        try {
            int parsedValue = Integer.parseInt(value);

            if (parsedValue <= 0) {
                throw new IllegalStateException(
                        variable + " must be greater than zero"
                );
            }

            return parsedValue;

        } catch (NumberFormatException ex) {
            throw new IllegalStateException(
                    variable + " must be a valid integer",
                    ex
            );
        }
    }
}
