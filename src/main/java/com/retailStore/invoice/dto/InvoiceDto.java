package com.retailStore.invoice.dto;

import java.util.List;

import com.retailStore.product.models.ProductDto;

public class InvoiceDto {
	private String totalAmountToPay;
	private List<ProductDto> listOfProducts;

	public String getTotalAmountToPay() {
		return totalAmountToPay;
	}

	public void setTotalAmountToPay(String totalAmountToPay) {
		this.totalAmountToPay = totalAmountToPay;
	}

	public List<ProductDto> getListOfProducts() {
		return listOfProducts;
	}

	public void setListOfProducts(List<ProductDto> listOfProducts) {
		this.listOfProducts = listOfProducts;
	}

}
