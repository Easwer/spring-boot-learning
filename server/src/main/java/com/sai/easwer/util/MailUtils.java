package com.sai.easwer.util;

import java.util.ArrayList;
import java.util.Properties;

import com.sai.easwer.constants.MessageConstants;
import com.sai.easwer.constants.SecurityConstants;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Mail server utility class to send email with the provided SMTP configuration.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-06-29 19:54:47
 * @modify date 2020-06-29 19:54:47
 */
@Slf4j
@Component
public class MailUtils {

  /**
   * Get mail sender with the smtp configuration from database.
   * 
   * @return {@link JavaMailSender}
   */
  private JavaMailSender getJavaMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost(GlobalSettingsUtil.getString(SecurityConstants.SMTP_HOST, ""));
    mailSender.setPort(GlobalSettingsUtil.getInt(SecurityConstants.SMTP_PORT, 587));
    mailSender.setUsername(GlobalSettingsUtil.getString(SecurityConstants.SMTP_USERNAME, null));
    mailSender.setPassword(GlobalSettingsUtil.getString(SecurityConstants.SMTP_PASSWORD, null));

    Properties props = mailSender.getJavaMailProperties();
    props.put(MessageConstants.MAIL_SMTP_AUTH,
        GlobalSettingsUtil.getString(SecurityConstants.SMTP_AUTH, MessageConstants.TRUE));
    props.put(MessageConstants.MAIL_SMTP_STARTTLS_ENABLE,
        GlobalSettingsUtil.getString(SecurityConstants.SMTP_TLS, MessageConstants.TRUE));
    props.put(MessageConstants.MAIL_DEBUG,
        GlobalSettingsUtil.getString(SecurityConstants.SMTP_DEBUG, MessageConstants.TRUE));

    return mailSender;
  }

  /**
   * Sends email to the provided mail recipients.
   * 
   * @param recipientList {@link ArrayList}<{@link String}>
   * @param subject       {@link String}
   * @param content       {@link String}
   * @throws Exception - If mail sending failed.
   */
  public void sendEmail(final ArrayList<String> recipientList, final String subject, final String content)
      throws Exception {
    try {
      final SimpleMailMessage msg = new SimpleMailMessage();
      String mails = "";
      for (final String mail : recipientList) {
        mails = mails + "," + mail;
      }
      msg.setTo(mails);
      msg.setSubject(subject);
      msg.setText(content);
      getJavaMailSender().send(msg);
    } catch (final Exception e) {
      log.error(MessageConstants.FAILED_TO_SEND_EMAIL_DUE_TO, e);
    }
  }

  /**
   * Sends email to the provided mail recipient.
   * 
   * @param recipientList {@link String}
   * @param subject       {@link String}
   * @param content       {@link String}
   * @throws Exception - If mail sending failed.
   */
  public void sendEmail(final String recipient, final String subject, final String content) throws Exception {
    try {
      final SimpleMailMessage msg = new SimpleMailMessage();
      msg.setTo(recipient);
      msg.setSubject(subject);
      msg.setText(content);
      getJavaMailSender().send(msg);
    } catch (final Exception e) {
      log.error(MessageConstants.FAILED_TO_SEND_EMAIL_DUE_TO, e);
    }
  }

}