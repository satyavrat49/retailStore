package com.retailStore.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;

import com.retailStore.user.dto.RoleDto;
import com.retailStore.user.dto.UserDto;

@Service
public class UserDao {
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public UserDto saveUser(UserDto userDto) {
		GeneratedKeyHolder holder = new GeneratedKeyHolder();
		String sql = "insert into Users(username,email,role_id,created_on,last_login) values (?,?,?,now(),now())";
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pss = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				pss.setString(1, userDto.getUserName());
				pss.setString(2, userDto.getEmail());
				pss.setInt(3, userDto.getRole().getRoleId());
				return pss;
			}
		}, holder);
		userDto.setUserId(Integer.parseInt(holder.getKeyList().get(0).get("user_id").toString()));
		return userDto;
	}

	public UserDto getUser(String email) {
		String sql = "select * from Users where email ilike ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { email }, (rs, row) -> {
			UserDto user = new UserDto();
			user.setUserId(rs.getInt("user_id"));
			user.setUserName(rs.getString("username"));
			user.setEmail(rs.getString("email"));
			user.setRole(getUserRole(rs.getInt("role_id")));
			user.setCreatedDate(rs.getDate("created_on"));
			return user;
		});

	}

	private RoleDto getUserRole(int roleId) {
		String sql = "select role_id roleId,role_name roleName from role where role_id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { roleId }, new BeanPropertyRowMapper<RoleDto>(RoleDto.class));
	}

	public void deleteUser(String email) {
		String sql = "delete from Users where email ilike ?";
		jdbcTemplate.update(sql, new Object[] { email });
	}

	public UserDto updateUser(UserDto userDto) {
		String sql = "update Users set email=?,username=?,role_id=? where user_id=? ";
		jdbcTemplate.update(sql, new Object[] { userDto.getEmail(), userDto.getUserName(),
				userDto.getRole().getRoleId(), userDto.getUserId() });
		return getUser(userDto.getEmail());
	}
}
