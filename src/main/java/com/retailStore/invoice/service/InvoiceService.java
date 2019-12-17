package com.retailStore.invoice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.retailStore.invoice.dao.InvoiceDao;
import com.retailStore.invoice.dto.InvoiceDto;
import com.retailStore.invoice.dto.PurchaseDto;

@Service
public class InvoiceService {
	
	@Autowired
	private InvoiceDao invoiceDao;

	public InvoiceDto createUserInvoice(PurchaseDto purchaseDto) {
		return invoiceDao.createUserInvoice(purchaseDto);
	} 

}
