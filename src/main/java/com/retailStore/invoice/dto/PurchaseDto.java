package com.retailStore.invoice.dto;

import java.util.List;

import com.retailStore.product.models.ProductDto;

public class PurchaseDto {
	private List<ProductDto> listOfProduct;
	private String userEmail;

	public List<ProductDto> getListOfProduct() {
		return listOfProduct;
	}

	public void setListOfProduct(List<ProductDto> listOfProduct) {
		this.listOfProduct = listOfProduct;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	

	

}
