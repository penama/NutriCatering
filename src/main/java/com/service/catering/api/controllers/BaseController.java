package com.service.catering.api.controllers;

import com.service.catering.application.service.logger.LoggerService;
import com.service.catering.application.utils.FormatJsonService;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {

	@Autowired
	FormatJsonService formatJsonService;

	@Autowired
	LoggerService log;

}
