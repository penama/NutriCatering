package com.service.catering.application.service.scheduled;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.service.catering.application.model.event.EventDto;
import com.service.catering.domain.model.ProducerEntity;
import com.service.catering.infraestructure.repositories.service.ProducerServiceRepository;
import com.service.catering.infraestructure.servicebus.IProducerBus;
import com.service.catering.infraestructure.utils.DateFormat;

import jakarta.annotation.PostConstruct;

@Component
public class ScheduledTaskService {
  @Autowired private CronProperties cronProperties;

  @Autowired private IProducerBus iProducerBus;

  @Autowired private ProducerServiceRepository producerServiceRepository;

  ObjectMapper mapper = null;

  @PostConstruct
  public void init() {
    mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
  }

  @Scheduled(cron = "#{@cronProperties.getExpression()}")
  public void executeTask() {
    List<ProducerEntity> producerEntityList = producerServiceRepository.queryProducers();
    for (ProducerEntity producerEntity : producerEntityList) {
      try {
        iProducerBus.sendMessage(mapper.readValue(producerEntity.body, EventDto.class));
        producerEntity.setStatus("completado");
      } catch (JsonProcessingException e) {
        e.printStackTrace();
        producerEntity.setStatus("error");
      } finally {
        producerEntity.setProcessedDate(DateFormat.toDate());
        producerServiceRepository.updateProducer(producerEntity);
      }
    }
  }
}
