package com.spark.lms.service;

import com.spark.lms.model.User;
import com.spark.lms.model.dto.UserDto;
import com.spark.lms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public List<UserDto> getAllUsers() {
		return userRepository.findAllByOrderByDisplayNameAsc().stream().map(UserDto::fromUser).collect(Collectors.toList());
	}
	
	
	public List<UserDto> getAllActiveUsers() { return
		userRepository.findAllByActiveOrderByDisplayNameAsc(1).stream().map(UserDto::fromUser).collect(Collectors.toList());
	}
	
	public UserDto getByUsername(String username) {
		return UserDto.fromUser(userRepository.findByUsername(username));
	}
	
	public UserDto getById(Long id) {
		return UserDto.fromUser(userRepository.findById(id).get());
	}
	
	public UserDto addNew(UserDto user) {
		user.setPassword( passwordEncoder.encode(user.getPassword()) );
		user.setCreatedDate( new Date() );
		user.setLastModifiedDate( user.getCreatedDate() );
		user.setActive(1);
		return UserDto.fromUser(userRepository.save(User.toUser(user)));
	}
	
	public UserDto update(UserDto user) {
		user.setLastModifiedDate( new Date() );
		return UserDto.fromUser(userRepository.save( User.toUser(user) ));
	}
	
	public void delete(UserDto user) {
		userRepository.delete(User.toUser(user));
	}
	
	public void delete(Long id) {
		userRepository.deleteById(id);
	}
}
