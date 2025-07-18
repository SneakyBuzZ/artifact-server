package tech.sneakybuzz.artifact.services.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import tech.sneakybuzz.artifact.dto.requests.VerifyAccountRequest;
import tech.sneakybuzz.artifact.services.MailService;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

  private final JavaMailSender mailSender;
  private final TemplateEngine templateEngine;

  @Value("${spring.mail.properties.mail.smtp.from}")
  private String fromEmail;

  @Async
  public void sendVerificationMail(String email, String name, String token){
    try{
      String activationLink = "http://localhost:5173/account/verify?token=" + token.toString();
      log.info("Activation link: {}", activationLink);
      Context context = new Context();
      context.setVariable("name", name);
      context.setVariable("activationLink", activationLink);
      String htmlContent = templateEngine.process("verify",context);

      MimeMessage mimeMessage = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

      helper.setTo(email);
      helper.setSubject("Verify your account");
      helper.setText(htmlContent, true);
      helper.setFrom(fromEmail);

      mailSender.send(mimeMessage);
    }catch(MessagingException e) {
      throw new RuntimeException("Failed to send verification email", e);
    } catch (Exception e) {
      throw new RuntimeException("Unexpected error occurred", e);
    }
  }
}

