package com.softserve.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailContent {
	private String to;
	private String subject;
	private String template;
	private Map<String, Object> model;
}
