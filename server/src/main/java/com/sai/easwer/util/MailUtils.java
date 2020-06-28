package com.sai.easwer.util;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class MailUtils {

  @Autowired
  private JavaMailSender javaMailSender;

  @Bean
  public JavaMailSender getJavaMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost("<smpt_server>");
    mailSender.setPort(587); // <smtp_port>

    mailSender.setUsername("<email_username>");
    mailSender.setPassword("<email_password>");

    Properties props = mailSender.getJavaMailProperties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.debug", "true");

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
      javaMailSender.send(msg);
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  public void sendEmail(final String mail, final String subject, final String content) throws Exception {
    try {
      final SimpleMailMessage msg = new SimpleMailMessage();
      // msg.setFrom("easwer.spring.boot@gmail.com");
      // msg.setReplyTo("easwer.spring.boot@gmail.com");
      msg.setTo(mail);
      msg.setSubject(subject);
      msg.setText(content);
      javaMailSender.send(msg);
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

}