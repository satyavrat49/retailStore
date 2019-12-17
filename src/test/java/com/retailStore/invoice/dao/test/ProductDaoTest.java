package com.retailStore.invoice.dao.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.retailStore.product.models.CategoryDto;
import com.retailStore.product.models.ProductDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest {

	private static int productId;

	/*
	 * @MockBean private JdbcTemplate jdbcTemplate;
	 * 
	 * @Test public void listOfProducts() { ProductDao productDao=new ProductDao();
	 * productDao.setJdbcTemplate(jdbcTemplate);
	 * assertTrue(productDao.getListOfProducts()!=null); }
	 * 
	 * @Test public void getCategory() { ProductDao productDao=new ProductDao();
	 * productDao.setJdbcTemplate(jdbcTemplate);
	 * assertTrue(productDao.getCategory(1)==null); }
	 */

	@Test
	// @Order(1)
	public void test1() throws InterruptedException {

		RestTemplate restTemplate = new RestTemplate();
		ProductDto productDto = new ProductDto();
		productDto.setProductName("testproduct");
		productDto.setProductCost("20");
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setCategoryId(1);
		productDto.setCategory(categoryDto);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ProductDto> entity = new HttpEntity<ProductDto>(productDto, headers);
		ResponseEntity<ProductDto> pr = restTemplate.exchange("http://localhost:" + "8080" + "/products/",
				HttpMethod.POST, entity, ProductDto.class);
		this.productId = pr.getBody().getProductId();
		assertTrue(pr.getBody().getProductId() != 0 && pr.getStatusCode() == HttpStatus.CREATED);
	}

	@Test
	public void test2() throws InterruptedException {
		RestTemplate restTemplate = new RestTemplate();
		ProductDto pr = restTemplate.getForObject("http://localhost:" + "8080" + "/products/" + this.productId,
				ProductDto.class);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		pr.setProductName("test" + pr.getProductName());
		HttpEntity<ProductDto> entity = new HttpEntity<ProductDto>(pr, headers);
		ResponseEntity<ProductDto> updateProduct = restTemplate.exchange("http://localhost:" + "8080" + "/products/",
				HttpMethod.PUT, entity, ProductDto.class);
		assertTrue(updateProduct.getBody().getProductId() == pr.getProductId()
				&& updateProduct.getStatusCode() == HttpStatus.OK && updateProduct.getBody().getProductName() != null);
	}

	@Test
	public void test3() throws InterruptedException {
		RestTemplate restTemplate = new RestTemplate();

		ProductDto pr = restTemplate.getForObject("http://localhost:" + "8080" + "/products/1", ProductDto.class);
		assertTrue(pr != null && pr.getCategory() != null);
	}

	@Test
	public void test4() throws InterruptedException {
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		List<ProductDto> pr = restTemplate.getForObject("http://localhost:" + "8080" + "/products/", List.class);
		assertTrue(pr != null);
	}

	@Test
	public void test5() throws InterruptedException {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete("http://localhost:" + "8080" + "/products/" + this.productId);
	}

}
