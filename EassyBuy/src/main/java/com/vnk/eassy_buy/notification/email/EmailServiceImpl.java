package com.vnk.eassy_buy.notification.email;

import java.io.IOException;
import java.util.Map;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
	private final JavaMailSender javaMailSender;
	private final Configuration configuration;

	@Override
	public void sendTemplateEmail(String to, String subject, String template, Map<String, Object> data)
			throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException {
		 try {

	            Template freeMarkerTemplate =
	                    configuration.getTemplate(
	                            template
	                    );

	            String htmlContent =
	                    FreeMarkerTemplateUtils
	                            .processTemplateIntoString(
	                                    freeMarkerTemplate,
	                                    data
	                            );

	            MimeMessage message =
	                    javaMailSender.createMimeMessage();

	            MimeMessageHelper helper =
	                    new MimeMessageHelper(
	                            message,
	                            true,
	                            "UTF-8"
	                    );

	            helper.setTo(to);
	            helper.setSubject(subject);
	            helper.setText(htmlContent, true);

	            javaMailSender.send(message);

	        } catch (Exception e) {

	            throw new RuntimeException(
	                    "Failed to send email",
	                    e
	            );
	        }
	}
}
