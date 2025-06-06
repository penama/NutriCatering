package com.service.catering.infraestructure.repositories.service;

import com.service.catering.domain.model.ContractEntity;
import com.service.catering.domain.model.ProducerEntity;
import com.service.catering.infraestructure.event.querys.IQueryContractRepository;
import com.service.catering.infraestructure.repositories.interfaces.ContractRepository;
import com.service.catering.infraestructure.repositories.interfaces.ProducerRepository;
import com.service.catering.infraestructure.utils.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class ProducerServiceRepository {

  @Autowired public ProducerRepository repository;

  public void newProducer(ProducerEntity producerEntity) {
	  producerEntity.id = UUID.randomUUID().toString();
	  producerEntity.createdDate = DateFormat.toDate();
    repository.save(producerEntity);
  }

  public List<ProducerEntity> queryProducers(){
	  List<String> status = Arrays.asList( "pendiente", "error" );
		return repository.findByStatusPendiente( status );
  }

	public void updateProducer(ProducerEntity producerEntity) {
		repository.save(producerEntity);
	}

}
