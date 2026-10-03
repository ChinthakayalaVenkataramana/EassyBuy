package com.vnk.eassy_buy.notification.email;

import java.io.IOException;
import java.util.Map;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateNotFoundException;

public interface EmailService {
	void sendTemplateEmail(String to, String subject, String template, Map<String, Object> data)
			throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException;

}
