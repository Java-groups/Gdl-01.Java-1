package com.softserve.util;

import java.util.Map;

public class EmailTemplate {
	
	private String from;
	  private String to;
	  String[] toList;
	  private String subject;
	  private String template;
	  private String logo;
	  private Map<String, Object> model;

	  public EmailTemplate(
	      String from,
	      String to,
	      String subject,
	      String template,
	      String logo,
	      Map<String, Object> model) {
	    this.from = from;
	    this.to = to;
	    this.subject = subject;
	    this.template = template;
	    this.logo = logo;
	    this.model = model;
	  }

	  public EmailTemplate(
	      String to, String subject, String template, String logo, Map<String, Object> model) {
	    this.to = to;
	    this.subject = subject;
	    this.template = template;
	    this.logo = logo;
	    this.model = model;
	  }

	  public EmailTemplate(
	      String[] toList,
	      String subject,
	      String template,
	      String logo,
	      Map<String, Object> model) {
	    this.toList = toList;
	    this.subject = subject;
	    this.template = template;
	    this.logo = logo;
	    this.model = model;
	  }

	  public String getFrom() {
	    return from;
	  }

	  public void setFrom(String from) {
	    this.from = from;
	  }

	  public String getTo() {
	    return to;
	  }

	  public void setTo(String to) {
	    this.to = to;
	  }

	  public String[] getToList() {
	    return toList;
	  }

	  public void setToList(String[] toList) {
	    this.toList = toList;
	  }

	  public String getSubject() {
	    return subject;
	  }

	  public void setSubject(String subject) {
	    this.subject = subject;
	  }

	  public String getTemplate() {
	    return template;
	  }

	  public void setTemplate(String template) {
	    this.template = template;
	  }

	  public String getLogo() {
	    return logo;
	  }

	  public void setLogo(String logo) {
	    this.logo = logo;
	  }

	  public Map<String, Object> getModel() {
	    return model;
	  }

	  public void setModel(Map<String, Object> model) {
	    this.model = model;
	  }

}
