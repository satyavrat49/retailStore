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

import com.retailStore.user.dto.RoleDto;
import com.retailStore.user.dto.UserDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {

	private static String email;

	@Test
	public void Test1() {
		UserDto userDto = new UserDto();
		userDto.setEmail("testEmployee@gmail.com");
		userDto.setUserName("test users");
		RoleDto roleDto = new RoleDto();
		roleDto.setRoleId(1);
		roleDto.setRoleName("Employee");
		userDto.setRole(roleDto);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<UserDto> entity = new HttpEntity<UserDto>(userDto, headers);
		ResponseEntity<UserDto> createUser = restTemplate.exchange("http://localhost:" + "8080" + "/users/",
				HttpMethod.POST, entity, UserDto.class);
		UserControllerTest.email = userDto.getEmail();
		assertTrue(createUser.getBody().getUserId() != 0 && createUser.getStatusCode() == HttpStatus.CREATED);

	}

	@Test
	public void Test2() {
		RestTemplate restTemplate = new RestTemplate();
		UserDto entity = restTemplate.getForObject("http://localhost:" + "8080" + "/users/" + UserControllerTest.email,
				UserDto.class);
		assertTrue(entity.getEmail().equalsIgnoreCase(UserControllerTest.email));
	}

	@Test
	public void Test3() {
		RestTemplate restTemplate = new RestTemplate();
		UserDto entity = restTemplate.getForObject("http://localhost:" + "8080" + "/users/" + UserControllerTest.email,
				UserDto.class);
		RoleDto role2 = entity.getRole();
		role2.setRoleId(2);
		entity.setRole(role2);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<UserDto> entity2 = new HttpEntity<UserDto>(entity, headers);
		ResponseEntity<UserDto> updatedUser = restTemplate.exchange("http://localhost:" + "8080" + "/users/",
				HttpMethod.PUT, entity2, UserDto.class);
		assertTrue((entity.getUserId() == updatedUser.getBody().getUserId())
				&& updatedUser.getBody().getRole().getRoleId() == 2);

	}

	@Test
	public void Test4() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete("http://localhost:" + "8080" + "/users/" + UserControllerTest.email);
	}

}
