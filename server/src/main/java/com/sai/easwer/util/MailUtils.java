package com.sai.easwer.util;

import java.util.Properties;

import com.sai.easwer.constants.MessageConstants;
import com.sai.easwer.constants.SecurityConstants;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class MailUtils {

  public JavaMailSender getJavaMailSender() {
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

  public void sendEmail(final String[] mailList, final String subject, final String content) throws Exception {
    try {
      final SimpleMailMessage msg = new SimpleMailMessage();
      String mails = "";
      for (final String mail : mailList) {
        mails = mails + "," + mail;
      }
      msg.setTo(mails);
      msg.setSubject(subject);
      msg.setText(content);
      getJavaMailSender().send(msg);
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  public void sendEmail(final String mail, final String subject, final String content) throws Exception {
    try {
      final SimpleMailMessage msg = new SimpleMailMessage();
      msg.setTo(mail);
      msg.setSubject(subject);
      msg.setText(content);
      getJavaMailSender().send(msg);
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

}