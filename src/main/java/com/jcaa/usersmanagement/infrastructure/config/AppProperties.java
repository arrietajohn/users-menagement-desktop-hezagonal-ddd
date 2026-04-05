package com.jcaa.usersmanagement.infrastructure.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public final class AppProperties {

  private static final String PROPERTIES_FILE = "application.properties";

  private final Properties properties;

  public AppProperties() {
    this.properties = loadProperties();
  }

  private static Properties loadProperties() {
    final Properties props = new Properties();
    try (final InputStream inputStream =
        AppProperties.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
      Objects.requireNonNull(inputStream, "No se encontró el archivo: " + PROPERTIES_FILE);
      props.load(inputStream);
    } catch (final IOException exception) {
      throw ConfigurationException.becauseLoadFailed(exception);
    }
    return props;
  }

  public String get(final String key) {
    final String value = properties.getProperty(key);
    Objects.requireNonNull(value, "Propiedad no encontrada en application.properties: " + key);
    return value;
  }

  public int getInt(final String key) {
    return Integer.parseInt(get(key));
  }
}
