package com.retailStore.user.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retailStore.product.exception.DataNotFoundException;
import com.retailStore.user.dao.UserDao;
import com.retailStore.user.dto.UserDto;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public UserDto saveUser(UserDto userDto) {
		
		return userDao.saveUser(userDto);
	}

	public UserDto getUser(String email) {
		if(StringUtils.isNoneBlank(email))
		return userDao.getUser(email);
		else
			throw new DataNotFoundException("no user exist");
	}

	public void deleteUser(String email) {
		 userDao.deleteUser(email);
	}

	public UserDto updateUser(UserDto userDto) {
		return userDao.updateUser(userDto);
	}
	
	
	
	
}
