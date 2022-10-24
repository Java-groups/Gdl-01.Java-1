package com.softserve.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeVerificationDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer firstNumber;
	
	private Integer secondNumber;
	
	private Integer thirdNumber;
	
	private Integer fourthNumber;
	
	private Integer fifthNumber;
}
