package com.jcaa.usersmanagement.infrastructure.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public final class AppProperties {

  private static final String PROPERTIES_FILE = "application.properties";

  private final Properties properties;

  public AppProperties() {
    final Properties loaded;
    try (final InputStream stream =
        AppProperties.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
      loaded = doLoad(stream);
    } catch (final IOException exception) {
      throw ConfigurationException.becauseLoadFailed(exception);
    }
    this.properties = loaded;
  }

  AppProperties(final InputStream stream) {
    this.properties = doLoad(stream);
  }

  private static Properties doLoad(final InputStream stream) {
    Objects.requireNonNull(stream, "File not found in classpath: " + PROPERTIES_FILE);
    final Properties props = new Properties();
    try {
      props.load(stream);
    } catch (final IOException exception) {
      throw ConfigurationException.becauseLoadFailed(exception);
    }
    return props;
  }

  public String get(final String key) {
    final String value = properties.getProperty(key);
    Objects.requireNonNull(value, "Property not found in " + PROPERTIES_FILE + ": " + key);
    return value;
  }

  public int getInt(final String key) {
    return Integer.parseInt(get(key));
  }
}
