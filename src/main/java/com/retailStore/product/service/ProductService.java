package com.retailStore.product.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retailStore.product.dao.ProductDao;
import com.retailStore.product.exception.DataNotFoundException;
import com.retailStore.product.exception.InvalidRequestPayloadException;
import com.retailStore.product.models.MessageDto;
import com.retailStore.product.models.ProductDto;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;

	public List<ProductDto> getListOfPorducts() {
		return productDao.getListOfProducts();
	}

	public ProductDto getPorduct(int productId) {
		if (productId != 0)
			return productDao.getPorduct(productId);
		else
			throw new DataNotFoundException("invalid product id");
	}

	public MessageDto deletePorduct(int productId) {
		return productDao.deletePorduct(productId);

	}

	public ProductDto createProduct(ProductDto productDto) {
		if (StringUtils.isNotBlank(productDto.getProductName()) && StringUtils.isNotBlank(productDto.getProductCost())
				&& doProductCategoryExist(productDto.getCategory().getCategoryId()))
			return productDao.createProduct(productDto);
		else
			throw new InvalidRequestPayloadException("unable to create the product");
	}

	private boolean doProductCategoryExist(int categoryid) {
		return productDao.doProductCategoryExist(categoryid);
	}

	public ProductDto updateProduct(ProductDto productDto) {
		ProductDto existingProduct = getPorduct(productDto.getProductId());
		if (!doProductCategoryExist(productDto.getCategory().getCategoryId())) {
			throw new DataNotFoundException("not a valid category");
		} else {
			productDao.updateProduct(productDto);
			return productDto;
		}
	}

	private boolean doTheProductCategoryChanged(ProductDto productUpdateDto, ProductDto existingProductDto) {
		if (productUpdateDto.getCategory() != null && Integer.compare(productUpdateDto.getCategory().getCategoryId(),
				existingProductDto.getCategory().getCategoryId()) != 0)
			return true;
		else
			return false;

	}

}
