package com.service.catering.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import com.service.catering.application.service.logger.LoggerService;
import com.service.catering.application.utils.FormatJsonService;

public class BaseController {

  @Autowired FormatJsonService formatJsonService;

  @Autowired LoggerService log;
}
