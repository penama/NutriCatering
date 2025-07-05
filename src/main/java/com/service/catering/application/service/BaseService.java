package com.service.catering.application.service;

import com.service.catering.application.service.logger.LoggerService;
import com.service.catering.application.utils.FormatJsonService;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {

  @Autowired FormatJsonService formatJsonService;

  @Autowired public LoggerService log;
}
