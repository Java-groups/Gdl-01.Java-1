package com.softserve.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordDTO {

	private String oldPassword;
	
	private String newPassword;
	
	private String repeatNewPassword;
	
	private String email;
}
