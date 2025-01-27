package com.service.catering.infraestructure.event.command;

import com.service.catering.domain.model.*;
import com.service.catering.infraestructure.repositories.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CommandEntitysEventListener implements ApplicationListener<CommandEntitysEvent> {

    @Autowired
    private PaymentServiceRepository paymentServiceRepository;

    @Autowired
    private PaymentMethodServiceRepository paymentMethodServiceRepository;

    @Autowired
    private CustomerServiceRepository customerServiceRepository;

    @Autowired
    private ContractServiceRepository contractServiceRepository;

    @Autowired
    private OrderServiceRepository orderServiceRepository;

    @Override
    public void onApplicationEvent(CommandEntitysEvent event) {
        if (event.getData() instanceof PaymentEntity) {
            try {
                paymentServiceRepository.newPayment((PaymentEntity) event.getData());
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        if (event.getData() instanceof PaymentMethodEntity) {
            try {
                paymentMethodServiceRepository.newPaymentMethod((PaymentMethodEntity) event.getData());
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        if (event.getData() instanceof CustomerEntity) {
            try {
                customerServiceRepository.newCustomer((CustomerEntity) event.getData());
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        if (event.getData() instanceof ContractEntity) {
            try {
                contractServiceRepository.newContract((ContractEntity) event.getData());
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        if (event.getData() instanceof OrderEntity) {
            try {
                orderServiceRepository.newContract((OrderEntity) event.getData());
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
