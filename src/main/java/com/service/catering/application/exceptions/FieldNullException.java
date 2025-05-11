package com.service.catering.application.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldNullException extends Exception {

  String message;
}
