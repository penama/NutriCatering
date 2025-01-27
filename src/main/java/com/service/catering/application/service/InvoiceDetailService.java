package com.service.catering.application.service;

import com.service.catering.application.model.invoice.InvoiceDetail;
import com.service.catering.application.service.interfaces.IInvoiceDetailServiceGenerate;
import com.service.catering.application.utils.InvoiceDetailUtil;
import com.service.catering.domain.model.InvoiceDetailEntity;
import com.service.catering.infraestructure.event.querys.IQueryInvoiceDetailRepository;
import com.service.catering.infraestructure.event.update.IGenerateInvoiceDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InvoiceDetailService extends BaseService implements IInvoiceDetailServiceGenerate {

    @Autowired
    private IQueryInvoiceDetailRepository iQueryInvoiceDetailRepository;

    @Autowired
    private IGenerateInvoiceDetailRepository iGenerateInvoiceDetailRepository;

//    @Transactional( propagation = Propagation.REQUIRED)
//    public void newInvoiceDetail(InvoiceDetail invoiceDetail) throws Exception {
//        ContractEntity contractEntity = ContractUtil.contractDtoToContractEntity( contractDto );
//        contractEntity.setStatus(ContractStatus.ACTIVE.name());
//        commandHandler( this, contractEntity );
//    }

    public List<InvoiceDetailEntity> getInvoiceDetails( String invoiceId ) throws  Exception{
        return iQueryInvoiceDetailRepository.queryInvoiceDetailByInvoice( invoiceId );
    }

    @Override
    @Transactional( propagation = Propagation.MANDATORY)
    public void generateInvoiceDetail(InvoiceDetail invoiceDetail) throws Exception {
        InvoiceDetailEntity invoiceDetailEntity = InvoiceDetailUtil.InvoiceDetailToInvoiceDetailEntity( invoiceDetail );
        iGenerateInvoiceDetailRepository.generateInvoiceDetail( invoiceDetailEntity );
    }
}
