package com.retailStore.user.controlres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retailStore.user.dto.UserDto;
import com.retailStore.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserContorler {
	@Autowired
	private UserService userService;

	@PostMapping(path = "/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		return new ResponseEntity(userService.saveUser(userDto), HttpStatus.CREATED);
	}

	@GetMapping(path = "/{email}")
	public ResponseEntity<UserDto> getUser(@PathVariable String email) {
		return new ResponseEntity(userService.getUser(email), HttpStatus.OK);
	}

	@DeleteMapping(path = "/{email}")
	public void deleteUser(@PathVariable String email) {
		userService.deleteUser(email);
	}

	@PutMapping(path = "/")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
		return new ResponseEntity<>(userService.updateUser(userDto), HttpStatus.OK);
	}

}
