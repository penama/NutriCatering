package com.service.catering.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.service.catering.application.model.invoice.InvoiceDetail;
import com.service.catering.application.model.invoice.InvoiceDto;
import com.service.catering.application.service.interfaces.IInvoiceServiceGenerate;
import com.service.catering.application.utils.InvoiceDetailUtil;
import com.service.catering.application.utils.InvoiceUtil;
import com.service.catering.domain.model.InvoiceDetailEntity;
import com.service.catering.domain.model.InvoiceEntity;
import com.service.catering.infraestructure.event.querys.IQueryInvoiceDetailRepository;
import com.service.catering.infraestructure.event.querys.IQueryInvoiceRepository;
import com.service.catering.infraestructure.event.update.IGenerateInvoiceDetailRepository;
import com.service.catering.infraestructure.event.update.IGenerateInvoiceRepository;

@Service
public class InvoiceService extends BaseCommandHandler implements IInvoiceServiceGenerate {

  @Autowired private IQueryInvoiceRepository iQueryInvoiceRepository;

  @Autowired private IQueryInvoiceDetailRepository iQueryInvoiceDetailRepository;

  @Autowired private IGenerateInvoiceRepository iGenerateInvoiceRepository;

  @Autowired private IGenerateInvoiceDetailRepository iGenerateInvoiceDetailRepository;

  public List<InvoiceDto> getInvoices() throws Exception {
    List<InvoiceEntity> invoiceEntities = iQueryInvoiceRepository.queryInvoices();
    List<InvoiceDto> invoiceDtos = new ArrayList<>();
    for (InvoiceEntity invoiceEntity : invoiceEntities) {
      InvoiceDto invoiceDto = InvoiceUtil.InvoiceEntityToInvoiceDto(invoiceEntity);
      List<InvoiceDetailEntity> invoiceDetailEntities =
          iQueryInvoiceDetailRepository.queryInvoiceDetailByInvoice(invoiceDto.getId());
      for (InvoiceDetailEntity invoiceDetailEntity : invoiceDetailEntities) {
        InvoiceDetail invoiceDetail =
            InvoiceDetailUtil.InvoiceDetailEntityToInvoiceDetail(invoiceDetailEntity);
        invoiceDto.addInvoiceDetail(invoiceDetail);
      }
      invoiceDtos.add(invoiceDto);
    }
    return invoiceDtos;
  }

  @Override
  @Transactional(propagation = Propagation.MANDATORY)
  public void generateInvoice(InvoiceDto invoiceDto) throws Exception {
    InvoiceEntity invoiceEntity = InvoiceUtil.InvoiceDtoToInvoiceEntity(invoiceDto);
    iGenerateInvoiceRepository.generateInvoice(invoiceEntity);
    for (InvoiceDetail invoiceDetail : invoiceDto.getInvoiceDetails()) {
      InvoiceDetailEntity invoiceDetailEntity =
          InvoiceDetailUtil.InvoiceDetailToInvoiceDetailEntity(invoiceDetail);
      invoiceDetailEntity.setInvoiceId(invoiceEntity.getId());
      iGenerateInvoiceDetailRepository.generateInvoiceDetail(invoiceDetailEntity);
    }
  }
}
