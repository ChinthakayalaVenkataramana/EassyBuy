package com.vnk.eassy_buy.constants;

public enum TemplateType {

	OTP("otp.ftl");

	private final String template;

	TemplateType(String template) {
		this.template = template;
	}

	public String getTemplate() {
		return template;
	}

}
