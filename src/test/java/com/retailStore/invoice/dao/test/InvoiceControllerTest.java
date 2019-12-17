package com.retailStore.invoice.dao.test;

import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.retailStore.invoice.dto.InvoiceDto;
import com.retailStore.invoice.dto.PurchaseDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InvoiceControllerTest {

	@Test
	public void Test1() throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		String requestEmployee = "{\"listOfProduct\":[{\"productId\":1,\"productName\":\"beans\",\"productCost\":\"10.0\",\"category\":{\"categoryId\":1,\"categoryName\":\"groceries\"}},{\"productId\":2,\"productName\":\"rice\",\"productCost\":\"10.0\",\"category\":{\"categoryId\":1,\"categoryName\":\"groceries\"}},{\"productId\":3,\"productName\":\"Phone\",\"productCost\":\"100.0\",\"category\":{\"categoryId\":2,\"categoryName\":\"electronics\"}},{\"productId\":4,\"productName\":\"TV\",\"productCost\":\"1000.0\",\"category\":{\"categoryId\":2,\"categoryName\":\"electronics\"}}],\"userEmail\":\"Ram@Gmail.com\"}";
		PurchaseDto purchaseDto = objectMapper.readValue(requestEmployee, PurchaseDto.class);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<PurchaseDto> entity = new HttpEntity<PurchaseDto>(purchaseDto, headers);
		ResponseEntity<InvoiceDto> createInvoice = restTemplate.exchange("http://localhost:" + "8080" + "/Invoice/",
				HttpMethod.POST, entity, InvoiceDto.class);
		System.out.println(createInvoice.getBody().getTotalAmountToPay());
		assertTrue(createInvoice.getStatusCode() == HttpStatus.CREATED);

	}

	@Test
	public void Test2() throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		String requestEmployee = "{\"listOfProduct\":[{\"productId\":1,\"productName\":\"beans\",\"productCost\":\"10.0\",\"category\":{\"categoryId\":1,\"categoryName\":\"groceries\"}},{\"productId\":2,\"productName\":\"rice\",\"productCost\":\"10.0\",\"category\":{\"categoryId\":1,\"categoryName\":\"groceries\"}},{\"productId\":3,\"productName\":\"Phone\",\"productCost\":\"100.0\",\"category\":{\"categoryId\":2,\"categoryName\":\"electronics\"}},{\"productId\":4,\"productName\":\"TV\",\"productCost\":\"1000.0\",\"category\":{\"categoryId\":2,\"categoryName\":\"electronics\"}}],\"userEmail\":\"shyam@Gmail.com\"}";
		PurchaseDto purchaseDto = objectMapper.readValue(requestEmployee, PurchaseDto.class);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<PurchaseDto> entity = new HttpEntity<PurchaseDto>(purchaseDto, headers);
		ResponseEntity<InvoiceDto> createInvoice = restTemplate.exchange("http://localhost:" + "8080" + "/Invoice/",
				HttpMethod.POST, entity, InvoiceDto.class);
		System.out.println(createInvoice.getBody().getTotalAmountToPay());
		assertTrue(createInvoice.getStatusCode() == HttpStatus.CREATED);

	}

	@Test
	public void Test3() throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		String requestEmployee = "{\"listOfProduct\":[{\"productId\":1,\"productName\":\"beans\",\"productCost\":\"10.0\",\"category\":{\"categoryId\":1,\"categoryName\":\"groceries\"}},{\"productId\":2,\"productName\":\"rice\",\"productCost\":\"10.0\",\"category\":{\"categoryId\":1,\"categoryName\":\"groceries\"}},{\"productId\":3,\"productName\":\"Phone\",\"productCost\":\"100.0\",\"category\":{\"categoryId\":2,\"categoryName\":\"electronics\"}},{\"productId\":4,\"productName\":\"TV\",\"productCost\":\"1000.0\",\"category\":{\"categoryId\":2,\"categoryName\":\"electronics\"}}],\"userEmail\":\"Dan@Gmail.com\"}";
		PurchaseDto purchaseDto = objectMapper.readValue(requestEmployee, PurchaseDto.class);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<PurchaseDto> entity = new HttpEntity<PurchaseDto>(purchaseDto, headers);
		ResponseEntity<InvoiceDto> createInvoice = restTemplate.exchange("http://localhost:" + "8080" + "/Invoice/",
				HttpMethod.POST, entity, InvoiceDto.class);
		System.out.println(createInvoice.getBody().getTotalAmountToPay());
		assertTrue(createInvoice.getStatusCode() == HttpStatus.CREATED);

	}

}
