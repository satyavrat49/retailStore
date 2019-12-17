package com.retailStore.invoice.dao;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.retailStore.invoice.dto.InvoiceDto;
import com.retailStore.invoice.dto.PurchaseDto;
import com.retailStore.product.dao.ProductDao;
import com.retailStore.product.models.ProductDto;
import com.retailStore.user.dao.UserDao;
import com.retailStore.user.dto.UserDto;

@Service
public class InvoiceDao {
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Autowired
	private UserDao userDao;
	@Autowired
	private ProductDao productDao;

	public InvoiceDto createUserInvoice(PurchaseDto purchaseDto) {
		InvoiceDto invoiceDto = new InvoiceDto();
		List<ProductDto> listOfNonGrosaryProducts = purchaseDto.getListOfProduct().stream()
				.filter(product -> !productDao.getCategory(product.getCategory().getCategoryId()).getCategoryName()
						.equalsIgnoreCase("groceries"))
				.collect(Collectors.toList());
		BigDecimal sumOfNonGroceries = purchaseDesicountOnNonGrousry(listOfNonGrosaryProducts,
				new BigDecimal(getPercentDescount(userDao.getUser(purchaseDto.getUserEmail()))));
		BigDecimal sumOfGroceries = purchaseDto.getListOfProduct().stream()
				.filter(product -> productDao.getCategory(product.getCategory().getCategoryId()).getCategoryName()
						.equalsIgnoreCase("groceries"))
				.map(product -> new BigDecimal(product.getProductCost()))
				.reduce(new BigDecimal("0"), (x, y) -> x.add(y));
		invoiceDto.setTotalAmountToPay(purchaseDesicountOnNonGrousry(sumOfNonGroceries.add(sumOfGroceries)) + "$");
		invoiceDto.setListOfProducts(purchaseDto.getListOfProduct());
		return invoiceDto;
	}

	private BigDecimal purchaseDesicountOnNonGrousry(List<ProductDto> listOfProucts, BigDecimal descount) {
		return listOfProucts.stream()
				.map(productDao -> new BigDecimal(productDao.getProductCost()).subtract(
						(new BigDecimal(productDao.getProductCost()).multiply(descount)).divide(new BigDecimal("100"))))
				.reduce(new BigDecimal("0"), (x, y) -> x.add(y));

	}

	public static void main(String args[]) {
		List<BigDecimal> testList = new ArrayList<BigDecimal>();
		testList.add(new BigDecimal("100"));
		testList.add(new BigDecimal("200"));
		testList.add(new BigDecimal("200"));
		System.out.println(testList.stream()
				.map(value -> value.subtract(value.multiply(new BigDecimal("5")).divide(new BigDecimal("100"))))
				.reduce(new BigDecimal("0"), (x, y) -> x.add(y)));
	}

	private String getPercentDescount(UserDto userDto) {
		String precent = "0";
		switch (userDto.getRole().getRoleId()) {
		case 1:
			precent = "30";
			break;
		case 2:
			precent = "20";
			break;
		case 3:
			Period period = Period.between(LocalDate.parse(userDto.getCreatedDate()+""), LocalDate.now());
			if (period.getYears() > 2)
				precent = "10";

		}
		return precent;
	}

	private BigDecimal purchaseDesicountOnNonGrousry(BigDecimal totalPurcahse) {
		return totalPurcahse.subtract(new BigDecimal(totalPurcahse.divide(new BigDecimal("100")).intValue() + "")
				.multiply(new BigDecimal(5)));

	}

}
