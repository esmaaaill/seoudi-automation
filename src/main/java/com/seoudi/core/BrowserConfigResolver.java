package com.seoudi.core;

import java.util.Locale;
import java.util.Set;

/**
 * Encapsulates browser selection rules and defaults.
 */
public final class BrowserConfigResolver {

    private static final Set<String> SUPPORTED_BROWSERS = Set.of("chrome", "firefox");

    private BrowserConfigResolver() {
    }

    public static String resolveBrowser(String candidate, String fallback) {
        String resolved = normalize(candidate).orElseGet(() -> normalize(fallback).orElse("firefox"));
        if (!SUPPORTED_BROWSERS.contains(resolved)) {
            throw new IllegalArgumentException("Unsupported browser value: " + resolved);
        }
        return resolved;
    }

    private static java.util.Optional<String> normalize(String value) {
        if (value == null || value.isBlank()) {
            return java.util.Optional.empty();
        }
        return java.util.Optional.of(value.trim().toLowerCase(Locale.ROOT));
    }
}
