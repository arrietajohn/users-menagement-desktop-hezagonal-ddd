package com.jcaa.usersmanagement.infrastructure.config;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for AppProperties.
 *
 * <p>Covers: constructor happy path, {@code doLoad()} exceptional branches (null stream and failing
 * stream), {@code get()} (existing key and missing key), and {@code getInt()} (valid numeric value
 * and non-numeric value).
 */
@DisplayName("AppProperties")
class AppPropertiesTest {

  private static final String KEY_STRING = "db.host";
  private static final String KEY_INT = "db.port";
  private static final String KEY_MISSING = "nonexistent.key";

  private static final String EXPECTED_STRING = "localhost";
  private static final int EXPECTED_INT = 3306;

  private AppProperties appProperties;

  @BeforeEach
  void setUp() {
    appProperties = new AppProperties();
  }

  // ── constructor — happy path

  @Test
  @DisplayName("constructor loads application.properties without throwing")
  void shouldLoadPropertiesFileWithoutThrowing() {
    // Assert (Arrange + Act done in @BeforeEach)
    assertNotNull(
        appProperties, "constructor must not throw when the file exists in the classpath");
  }

  // ── doLoad() — null stream (file not found)

  @Test
  @DisplayName("constructor(InputStream) loads properties from a valid stream")
  void shouldLoadPropertiesFromValidStream() throws IOException {
    // Arrange
    final String content = "custom.key=custom.value\n";

    // Act + Assert
    try (final InputStream stream =
        new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8))) {
      final AppProperties loaded = new AppProperties(stream);
      assertEquals(
          "custom.value",
          loaded.get("custom.key"),
          "must load the property value from the injected stream");
    }
  }

  @Test
  @DisplayName(
      "constructor(InputStream) throws NullPointerException when stream is null (file not found)")
  void shouldThrowNullPointerExceptionWhenStreamIsNull() {
    // Arrange
    // Act + Assert
    assertThrows(
        NullPointerException.class,
        () -> new AppProperties(null),
        "must throw NullPointerException when the InputStream is null");
  }

  // ── doLoad() — IOException on read

  @Test
  @DisplayName("constructor throws ConfigurationException when the stream fails on read")
  void shouldThrowConfigurationExceptionOnIOException() throws IOException {
    // Arrange — InputStream that throws IOException on every read attempt
    try (final InputStream failingStream =
        new InputStream() {
          @Override
          public int read() throws IOException {
            throw new IOException("Simulated read failure");
          }

          @Override
          public int read(final byte[] buffer, final int offset, final int length)
              throws IOException {
            throw new IOException("Simulated read failure");
          }
        }) {

      // Act + Assert
      assertThrows(
          ConfigurationException.class,
          () -> new AppProperties(failingStream),
          "must throw ConfigurationException when the InputStream throws IOException on read");
    }
  }

  // ── get()

  @Test
  @DisplayName("get() returns the correct value for an existing key")
  void shouldReturnCorrectValueForExistingKey() {
    // Act
    final String result = appProperties.get(KEY_STRING);

    // Assert
    assertEquals(
        EXPECTED_STRING,
        result,
        "value must match the one defined in the test application.properties");
  }

  @Test
  @DisplayName("get() throws NullPointerException when the key does not exist")
  void shouldThrowNullPointerExceptionForMissingKey() {
    // Act + Assert
    final NullPointerException exception =
        assertThrows(NullPointerException.class, () -> appProperties.get(KEY_MISSING));

    assertTrue(
        exception.getMessage().contains(KEY_MISSING),
        "error message must include the searched key to ease diagnosis");
  }

  // ── getInt()

  @Test
  @DisplayName("getInt() returns the correct integer for a numeric key")
  void shouldReturnParsedIntForNumericKey() {
    // Act
    final int result = appProperties.getInt(KEY_INT);

    // Assert
    assertEquals(
        EXPECTED_INT,
        result,
        "integer value must match the one defined in the test application.properties");
  }

  @Test
  @DisplayName("getInt() throws NumberFormatException when the value is not a valid integer")
  void shouldThrowNumberFormatExceptionForNonIntegerValue() {
    // Act + Assert
    assertThrows(
        NumberFormatException.class,
        () -> appProperties.getInt(KEY_STRING),
        "must throw NumberFormatException when the key value is a non-numeric string");
  }
}
