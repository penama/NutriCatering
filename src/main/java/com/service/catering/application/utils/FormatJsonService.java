package com.service.catering.application.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FormatJsonService {

	private ObjectMapper objectMapper;

	@PostConstruct
	private void init(){
		objectMapper = new ObjectMapper();
	}

	public String objectToString( Object obj ) throws Exception{
		return objectMapper.writeValueAsString( obj );
	}

}
