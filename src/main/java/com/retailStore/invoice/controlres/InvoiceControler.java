package com.retailStore.invoice.controlres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retailStore.invoice.dto.InvoiceDto;
import com.retailStore.invoice.dto.PurchaseDto;
import com.retailStore.invoice.service.InvoiceService;

@RestController
@RequestMapping("/Invoice")
public class InvoiceControler {

	@Autowired
	private InvoiceService invoiceService;

	@PostMapping(path="/")
	public ResponseEntity<InvoiceDto> createUserInvoice(@RequestBody PurchaseDto purchaseDto) {
		return new ResponseEntity<>(invoiceService.createUserInvoice(purchaseDto), HttpStatus.CREATED);
	}

}
