package com.practice.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.practice.blog.entities.User;
import com.practice.blog.payloads.UserDto;
import com.practice.blog.payloads.UserResponse;
import com.practice.blog.repositories.UserRepo;
import com.practice.blog.services.UserService;
import com.practice.blog.exceptions.*;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		
		User user=this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		
		
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		
		User updatedUser=this.userRepo.save(user);
		
		UserDto userDto1=userToDto(updatedUser);
		
		return userDto1;
		
		
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));

		
		
		return this.userToDto(user);
	}

	@Override
	public UserResponse getAllUsers(Integer pageNumber,Integer pageSize) {
		// TODO Auto-generated method stub
		
		Pageable p=PageRequest.of(pageNumber, pageSize);
		
		Page<User> pageUser = this.userRepo.findAll(p);
		
		
		List<User> allUser = pageUser.getContent();
		
		
		//List<User> users= this.userRepo.findAll();
		List<UserDto> userDtos = allUser.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		
		UserResponse userResponse=new UserResponse();
		
		userResponse.setUsers(userDtos);
		userResponse.setPageNumber(pageUser.getNumber());
		userResponse.setPageSize(pageUser.getSize());
		userResponse.setTotalElements(pageUser.getTotalElements());
		userResponse.setTotalPages(pageUser.getTotalPages());
		userResponse.setLastPage(pageUser.isLast());
		
		
		return userResponse;
		
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		this.userRepo.delete(user);
	}
	
	
	public User dtoToUser(UserDto userDto) {
		
		
		User user=this.modelMapper.map(userDto, User.class);
		
		
//		User user=new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		return user;
		
	}
	
	public UserDto userToDto(User user) {
		
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
		
		
//		UserDto userDto=new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}

	
	
}
