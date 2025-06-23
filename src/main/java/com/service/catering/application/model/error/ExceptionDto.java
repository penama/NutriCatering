package com.service.catering.application.model.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionDto extends  Exception{

	private String message;

}
