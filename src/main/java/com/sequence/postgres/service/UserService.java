package com.sequence.postgres.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sequence.postgres.entity.UserEntity;
import com.sequence.postgres.model.User;
import com.sequence.postgres.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	public User createUser(User user) {
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);
		UserEntity newUserEntity = userRepository.save(userEntity);
		return modelMapper.map(newUserEntity, User.class);
	}

	public List<User> getAllUsers() {

		return userRepository.findAll().stream().map(entity -> modelMapper.map(entity, User.class))
				.collect(Collectors.toList());

	}

	public void deleteUser(long id) {

		userRepository.deleteById(id);

	}

	public User updateUser(User user, long id) {
		UserEntity existingUser = userRepository.findById(id).get();
		existingUser.setName(user.getName());
		existingUser.setEmail(user.getEmail());
		UserEntity updatedEntity = userRepository.save(existingUser);

		return modelMapper.map(updatedEntity, User.class);
	}

	public User getUser(long id) {
		UserEntity entity = userRepository.findById(id).get();

		return modelMapper.map(entity, User.class);
	}

}