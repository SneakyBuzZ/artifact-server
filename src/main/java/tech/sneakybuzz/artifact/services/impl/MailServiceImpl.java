package tech.sneakybuzz.artifact.services.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import tech.sneakybuzz.artifact.dto.requests.VerifyAccountRequest;

@Service
@RequiredArgsConstructor
public class MailServiceImpl {

  private final JavaMailSender mailSender;
  private final TemplateEngine templateEngine;

  @Value("${spring.mail.properties.mail.smtp.from}")
  private String fromEmail;

  @Async
  public void sendVerificationMail(VerifyAccountRequest request){
    try{
      Context context = new Context();
      context.setVariable("name", "Kaushik");
      String htmlContent = templateEngine.process("welcome",context);

      MimeMessage mimeMessage = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

      helper.setTo(request.getEmail());
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

