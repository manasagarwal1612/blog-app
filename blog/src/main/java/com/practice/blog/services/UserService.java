package com.practice.blog.services;

import java.util.List;


import com.practice.blog.payloads.UserDto;
import com.practice.blog.payloads.UserResponse;
public interface UserService {

	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getUserById(Integer userId);
	UserResponse getAllUsers(Integer pageNumber, Integer pageSize);
	void deleteUser(Integer userId);
	
	
}
