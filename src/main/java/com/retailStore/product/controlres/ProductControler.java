package com.retailStore.product.controlres;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retailStore.product.models.MessageDto;
import com.retailStore.product.models.ProductDto;
import com.retailStore.product.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductControler {
	@Autowired
	private ProductService productService;

	@GetMapping(path = "/")
	public ResponseEntity<List<ProductDto>> getListOfPorducts() {
		return new ResponseEntity<>(productService.getListOfPorducts(), HttpStatus.OK);
	}
	
	
	@GetMapping(path = "/{productId}")
	public ResponseEntity<ProductDto> getPorduct(@PathVariable int  productId ) {
		return new ResponseEntity<>(productService.getPorduct(productId), HttpStatus.OK);
	}

	@DeleteMapping(path = "/{productId}")
	public ResponseEntity<MessageDto> deletePorduct(@PathVariable int  productId ) {
		return new ResponseEntity<>(productService.deletePorduct(productId), HttpStatus.OK);
	}
	
	@PostMapping(path = "/")
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto ){
		return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/")
	public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto ){
		return new ResponseEntity<>(productService.updateProduct(productDto), HttpStatus.OK);
	}
}
