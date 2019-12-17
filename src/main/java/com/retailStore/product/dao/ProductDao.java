package com.retailStore.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.retailStore.product.models.CategoryDto;
import com.retailStore.product.models.MessageDto;
import com.retailStore.product.models.ProductDto;

@Component
@Transactional
public class ProductDao {

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<ProductDto> getListOfProducts() {
		final String sql = "select * from product ";
		return jdbcTemplate.query(sql, (rs, row) -> {
			ProductDto product = new ProductDto();
			product.setProductId(rs.getInt("product_id"));
			product.setProductName(rs.getString("product_name"));
			product.setProductCost(rs.getDouble("product_cost") + "");
			product.setCategory(getCategory(rs.getInt("category_id")));
			return product;
		});

	}

	public CategoryDto getCategory(int categoryId) {
		final String sql = "select category_id categoryId,category_name categoryName  from product_categories where category_id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { categoryId },
				new BeanPropertyRowMapper<CategoryDto>(CategoryDto.class));
	}

	public ProductDto getPorduct(int productId) {
		final String sql = "select * from product where product_id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { productId }, (rs, row) -> {
			ProductDto product = new ProductDto();
			product.setProductId(rs.getInt("product_id"));
			product.setProductName(rs.getString("product_name"));
			product.setProductCost(rs.getDouble("product_cost") + "");
			product.setCategory(getCategory(rs.getInt("category_id")));
			return product;
		});

	}

	public MessageDto deletePorduct(int productId) {
		MessageDto messageDto = new MessageDto();
		String sql = "delete from product where product_id=?";
		if (jdbcTemplate.update(sql, new Object[] { productId }) > 0)
			messageDto.setMessage("product deleted succeesfully");
		else
			messageDto.setMessage("no product found ");
		return messageDto;
	}

	public boolean doProductCategoryExist(int categoryid) {
		String sql = "select count(*) from  product_categories where category_id=? ";
		return jdbcTemplate.queryForObject(sql, new Object[] { categoryid }, Integer.class) > 0 ? true : false;
	}

	public ProductDto createProduct(ProductDto productDto) {
		String sql = "insert into product(product_name,product_cost,category_id) values (?,?,?)";
		GeneratedKeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pss = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				pss.setString(1, productDto.getProductName());
				pss.setString(2, productDto.getProductCost());
				pss.setInt(3, productDto.getCategory().getCategoryId());
				return pss;
			}
		}, holder);
		//System.out.println(holder.getKey().intValue());;
		productDto.setProductId(Integer.parseInt(holder.getKeyList().get(0).get("product_id").toString()));
		return productDto;
	}

	public int updateProduct(ProductDto productDto) {
		String sql = "update product set product_name=?,product_cost=?,category_id=? where product_id=?";
		return jdbcTemplate.update(sql, new Object[] { productDto.getProductName(), productDto.getProductCost(),
				productDto.getCategory().getCategoryId(), productDto.getProductId() });
	}

}
