package com.service.catering.api.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.service.catering.application.model.contract.CateringPlan;
import com.service.catering.application.model.contract.ContractDto;
import com.service.catering.application.model.contract.ContractStatus;
import com.service.catering.application.model.contract.Customer;
import com.service.catering.application.service.ContractService;

@WebMvcTest(ContractController.class)
public class ContractControllerTest {

  @Autowired private MockMvc mockMvc; // Simula llamadas HTTP

  @MockitoBean private ContractService contractService; // Mock del servicio

  @Test
  void testNewContract_Success() throws Exception {
    // Simular que el servicio no lanza excepciones
    doNothing().when(contractService).newContract(any(ContractDto.class));
    String bodyRequest =
        "{\"description\":\"contrato de catering por 10 meses\",\"cateringPlan\":{\"id\":\"{{$guid}}\"},\"customer\":{\"id\":\"c9d4ac7b-657c-4dd4-87a8-fe02887e2563\"},\"totalAmount\":\"5000\",\"quotas\":\"5\"}";
    // Simular llamada HTTP POST al endpoint
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/api/v1/catering/contract")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyRequest))
        .andExpect(status().isOk());

    // Verificar que el servicio fue llamado
    verify(contractService, Mockito.times(1)).newContract(any(ContractDto.class));
  }

  @Test
  void testNewContract_WhenExceptionThrown() throws Exception {
    // Simular que el servicio lanza una excepción
    doThrow(new RuntimeException("Error al procesar contrato"))
        .when(contractService)
        .newContract(any(ContractDto.class));

    String bodyRequest =
        "{\"description\":\"contrato de catering por 10 meses\",\"cateringPlan\":{\"id\":\"{{$guid}}\"},\"customer\":{\"id\":\"c9d4ac7b-657c-4dd4-87a8-fe02887e2563\"},\"totalAmount\":\"5000\",\"quotas\":\"5\"}";
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/api/v1/catering/contract")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyRequest))
        .andExpect(status().isInternalServerError());

    // Verificar que el servicio fue llamado antes del error
    verify(contractService, Mockito.times(1)).newContract(any(ContractDto.class));
  }

  @Test
  void testGetContracts_Success() throws Exception {
    // Simular que el servicio no lanza excepciones
    Customer customer = new Customer();
    customer.id = UUID.randomUUID().toString();

    CateringPlan cateringPlan = new CateringPlan();
    cateringPlan.id = UUID.randomUUID().toString();

    ContractDto contractDto = new ContractDto();
    contractDto.id = UUID.randomUUID().toString();
    contractDto.customer = customer;
    contractDto.status = ContractStatus.ACTIVE.name();
    contractDto.cateringPlan = cateringPlan;
    contractDto.creationDate = "21/02/2025";
    contractDto.quotas = 4;
    contractDto.totalAmount = 100;
    contractDto.description = "Test";
    List<ContractDto> contractDtoList = Arrays.asList(contractDto);
    // Simular que el servicio no lanza excepciones
    when(contractService.getContracts()).thenReturn(contractDtoList);
    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/v1/catering/contracts"))
        .andExpect(status().isOk());
    verify(contractService, Mockito.times(1)).getContracts();
  }

  @Test
  void testGetContracts_WhenExceptionThrown() throws Exception {
    // Simular que el servicio no lanza excepciones
    Customer customer = new Customer();
    customer.id = UUID.randomUUID().toString();

    CateringPlan cateringPlan = new CateringPlan();
    cateringPlan.id = UUID.randomUUID().toString();

    ContractDto contractDto = new ContractDto();
    contractDto.id = UUID.randomUUID().toString();
    contractDto.customer = customer;
    contractDto.status = ContractStatus.ACTIVE.name();
    contractDto.cateringPlan = cateringPlan;
    contractDto.creationDate = "21/02/2025";
    contractDto.quotas = 4;
    contractDto.totalAmount = 100;
    contractDto.description = "Test";
    List<ContractDto> contractDtoList = Arrays.asList(contractDto);
    // Simular que el servicio lanza una excepción
    doThrow(new RuntimeException("Error al procesar contrato"))
        .when(contractService)
        .getContracts();
    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/v1/catering/contracts"))
        .andExpect(status().isInternalServerError());

    // Verificar que el servicio fue llamado antes del error
    verify(contractService, Mockito.times(1)).getContracts();
  }

  @Test
  void testGetContractId_Success() throws Exception {
    // Simular que el servicio no lanza excepciones
    Customer customer = new Customer();
    customer.id = UUID.randomUUID().toString();

    CateringPlan cateringPlan = new CateringPlan();
    cateringPlan.id = UUID.randomUUID().toString();

    ContractDto contractDto = new ContractDto();
    contractDto.id = UUID.randomUUID().toString();
    contractDto.customer = customer;
    contractDto.status = ContractStatus.ACTIVE.name();
    contractDto.cateringPlan = cateringPlan;
    contractDto.creationDate = "21/02/2025";
    contractDto.quotas = 4;
    contractDto.totalAmount = 100;
    contractDto.description = "Test";
    List<ContractDto> contractDtoList = Arrays.asList(contractDto);

    when(contractService.getContract(anyString())).thenReturn(contractDto);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/v1/catering/contract/" + contractDto.id))
        .andExpect(status().isOk());
    verify(contractService, Mockito.times(1)).getContract(contractDto.id);
  }

  @Test
  void testGetContractId_WhenExceptionThrown() throws Exception {
    // Simular que el servicio no lanza excepciones
    Customer customer = new Customer();
    customer.id = UUID.randomUUID().toString();

    CateringPlan cateringPlan = new CateringPlan();
    cateringPlan.id = UUID.randomUUID().toString();

    ContractDto contractDto = new ContractDto();
    contractDto.id = UUID.randomUUID().toString();
    contractDto.customer = customer;
    contractDto.status = ContractStatus.ACTIVE.name();
    contractDto.cateringPlan = cateringPlan;
    contractDto.creationDate = "21/02/2025";
    contractDto.quotas = 4;
    contractDto.totalAmount = 100;
    contractDto.description = "Test";
    List<ContractDto> contractDtoList = Arrays.asList(contractDto);

    when(contractService.getContracts()).thenReturn(contractDtoList);

    doThrow(new RuntimeException("Error al procesar contrato"))
        .when(contractService)
        .getContract(anyString());
    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/v1/catering/contract/" + contractDto.id))
        .andExpect(status().isInternalServerError());

    // Verificar que el servicio fue llamado antes del error
    verify(contractService, Mockito.times(1)).getContract(contractDto.id);
  }
}
