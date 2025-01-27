package com.service.catering.application.service;

import com.service.catering.application.model.invoice.InvoiceDetail;
import com.service.catering.application.model.invoice.InvoiceDto;
import com.service.catering.application.model.payment.*;
import com.service.catering.application.service.interfaces.IBillerDataServiceUpdateData;
import com.service.catering.application.service.interfaces.IInvoiceServiceGenerate;
import com.service.catering.application.service.interfaces.IOrderServiceUpdateStatus;
import com.service.catering.application.utils.PaymentUtil;
import com.service.catering.domain.model.PaymentEntity;
import com.service.catering.infraestructure.event.querys.IQueryPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService extends BaseService{

    @Autowired
    private IQueryPaymentRepository iQueryPaymentRepository;

    @Autowired
    private IOrderServiceUpdateStatus iOrderServiceUpdateStatus;

    @Autowired
    private IBillerDataServiceUpdateData iBillerDataServiceUpdateData;

    @Autowired
    private IInvoiceServiceGenerate iInvoiceServiceGenerate;

    @Transactional( propagation = Propagation.REQUIRED)
    public void newPayment(PaymentDto paymentDto) throws Exception {
        PaymentEntity paymentEntity = PaymentUtil.paymentDtoToPaymentEntity( paymentDto );
        paymentEntity.setStatus( PaymentStatus.PAID.name() );
        commandHandler( this, paymentEntity );
        iOrderServiceUpdateStatus.actualizarStatus( paymentEntity.getOrderId() );
        iBillerDataServiceUpdateData.updateBillerData(
                paymentDto.getBillingInvoice().getCustomerId(),
                paymentDto.getBillingInvoice().getBillerData().getSocialReason(),
                paymentDto.getBillingInvoice().getBillerData().getNit(),
                paymentDto.getBillingInvoice().getBillerData().getEmail() );

        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setCustomerSocialReason( paymentDto.getBillingInvoice().getBillerData().getSocialReason() );
        invoiceDto.setCustomerNit( paymentDto.getBillingInvoice().getBillerData().getNit() );
        invoiceDto.setCustomerEmail( paymentDto.getBillingInvoice().getBillerData().getEmail() );
        invoiceDto.setTotal( paymentDto.getPrice().getAmount() );
        invoiceDto.setPaymentId( paymentDto.getId() );
        invoiceDto.setCurrency( paymentDto.getPrice().getCurrency() );

        InvoiceDetail invoiceDetail = new InvoiceDetail();
        invoiceDetail.setAmount( 1 );
        invoiceDetail.setConcepts( "PlanCatering" );
        invoiceDetail.setDescription( "PlanCatering" );
        invoiceDetail.setCurrency( paymentDto.getPrice().getCurrency() );
        invoiceDetail.setDiscount( 0 );
        invoiceDetail.setUnitPrice( paymentDto.getPrice().getAmount() );
        invoiceDetail.setSubtotal( paymentDto.getPrice().getAmount() );

        invoiceDto.addInvoiceDetail( invoiceDetail );

        iInvoiceServiceGenerate.generateInvoice( invoiceDto );
    }

    public List<PaymentDto> getPayments() throws Exception {
        List<PaymentEntity> paymentEntities = iQueryPaymentRepository.queryPayment();
        List<PaymentDto> paymentDtos = new ArrayList<>();
        for (PaymentEntity paymentEntity : paymentEntities) {
            paymentDtos.add( PaymentUtil.paymentEntityToPaymentDto( paymentEntity ) );
        }
        return paymentDtos;
    }

    public List<PaymentDto> getPaymentsByOrderId( String orderId ) throws Exception {
        List<PaymentEntity> paymentEntities = iQueryPaymentRepository.queryPaymentsByOrderId( orderId );
        List<PaymentDto> paymentDtos = new ArrayList<>();
        for (PaymentEntity paymentEntity : paymentEntities) {
            paymentDtos.add( PaymentUtil.paymentEntityToPaymentDto( paymentEntity ) );
        }
        return paymentDtos;
    }

}
