package com.seoudi.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BrowserConfigResolverTest {

    @Test
    @DisplayName("Should normalize uppercase browser values")
    void shouldNormalizeInput() {
        assertEquals("chrome", BrowserConfigResolver.resolveBrowser(" CHROME ", "firefox"));
    }

    @Test
    @DisplayName("Should use fallback value when candidate is blank")
    void shouldUseFallback() {
        assertEquals("firefox", BrowserConfigResolver.resolveBrowser(" ", "firefox"));
    }

    @Test
    @DisplayName("Should fail for unsupported browser")
    void shouldFailForUnsupportedBrowser() {
        assertThrows(IllegalArgumentException.class, () -> BrowserConfigResolver.resolveBrowser("safari", "firefox"));
    }
}
