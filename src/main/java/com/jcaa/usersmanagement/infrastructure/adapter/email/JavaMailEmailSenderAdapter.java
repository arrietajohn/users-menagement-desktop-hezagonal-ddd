package com.jcaa.usersmanagement.infrastructure.adapter.email;

import com.jcaa.usersmanagement.application.port.out.EmailSenderPort;
import com.jcaa.usersmanagement.domain.exception.EmailSenderException;
import com.jcaa.usersmanagement.domain.model.EmailDestinationModel;
import lombok.extern.java.Log;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;

@Log
public final class JavaMailEmailSenderAdapter implements EmailSenderPort {

  private static final String MAIL_SMTP_HOST = "mail.smtp.host";
  private static final String MAIL_SMTP_PORT = "mail.smtp.port";
  private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
  private static final String MAIL_SMTP_STARTTLS = "mail.smtp.starttls.enable";
  private static final String CONTENT_TYPE_HTML = "text/html; charset=UTF-8";
  private static final String CHARSET_UTF8 = "UTF-8";
  private static final String SENDER_EMAIL_LOG = "Correo enviado exitosamente a: {0}";

  private final Session mailSession;
  private final String fromAddress;
  private final String fromName;

  public JavaMailEmailSenderAdapter(final SmtpConfig config) {
    this.fromAddress = config.fromAddress();
    this.fromName = config.fromName();
    this.mailSession = buildSession(config);
  }

  @Override
  public void send(final EmailDestinationModel destination) {
    try {
      final MimeMessage message = buildMessage(destination);
      Transport.send(message);
      log.log(Level.INFO, SENDER_EMAIL_LOG, destination.getDestinationEmail());
    } catch (final MessagingException | UnsupportedEncodingException exception) {
      throw EmailSenderException.becauseSmtpFailed(
          destination.getDestinationEmail(), exception.getMessage());
    }
  }

  private MimeMessage buildMessage(final EmailDestinationModel destination)
      throws MessagingException, UnsupportedEncodingException {
    final MimeMessage message = new MimeMessage(mailSession);
    message.setFrom(new InternetAddress(fromAddress, fromName, CHARSET_UTF8));
    message.addRecipient(
        Message.RecipientType.TO,
        new InternetAddress(
            destination.getDestinationEmail(), destination.getDestinationName(), CHARSET_UTF8));
    message.setSubject(destination.getSubject(), CHARSET_UTF8);
    message.setContent(destination.getBody(), CONTENT_TYPE_HTML);
    return message;
  }

  private static Session buildSession(final SmtpConfig config) {
    final Properties properties = buildSmtpProperties(config);
    return Session.getInstance(
        properties,
        new Authenticator() {
          @Override
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(config.username(), config.password());
          }
        });
  }

  private static Properties buildSmtpProperties(final SmtpConfig config) {
    final Properties properties = new Properties();
    properties.put(MAIL_SMTP_HOST, config.host());
    properties.put(MAIL_SMTP_PORT, String.valueOf(config.port()));
    properties.put(MAIL_SMTP_AUTH, "true");
    properties.put(MAIL_SMTP_STARTTLS, "true");
    return properties;
  }
}
