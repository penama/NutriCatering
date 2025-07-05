package com.service.catering.application.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.service.catering.application.service.logger.LoggerService;
import com.service.catering.application.utils.FormatJsonService;

public class BaseService {

  @Autowired FormatJsonService formatJsonService;

  @Autowired public LoggerService log;
}
