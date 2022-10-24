package com.softserve.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.softserve.dto.EmailContent;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class Email {

	private JavaMailSender emailSender;
	private Configuration freemarkerConfig;
	private String from;

	public Email(@Value("${spring.mail.username}") String from, JavaMailSender emailSender,
			Configuration freemarkerConfig) {
		this.from = from;
		this.emailSender = emailSender;
		this.freemarkerConfig = freemarkerConfig;
	}

	@Async
	public void sendMessage(EmailContent EmailContent) throws MailException, InterruptedException, ExecutionException,
			MessagingException, IOException, TemplateException {

		emailSender.send(constructMailTemplate(EmailContent, false).get());
	}

	@Async
	public CompletableFuture<MimeMessage> constructMailTemplate(EmailContent emailContent, boolean isToList)
			throws IOException, TemplateException, MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());

		freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/mail-templates");
		freemarkerConfig.setNumberFormat("#");

		Template template = freemarkerConfig.getTemplate(emailContent.getTemplate());
		String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, emailContent.getModel());

		helper.setText(html, true);
		helper.setSubject(emailContent.getSubject());

		helper.setFrom(from);
		helper.setTo(emailContent.getTo());

		return CompletableFuture.completedFuture(message);
	}
}
