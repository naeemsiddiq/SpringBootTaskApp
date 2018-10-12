package com.example.taskapp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.taskapp.bean.UserBean;
import com.example.taskapp.dto.Task;
import com.example.taskapp.dto.User;
import com.example.taskapp.exception.ApiException;
import com.example.taskapp.mapper.UserMapper;
import com.example.taskapp.repository.UserRepository;
import com.example.taskapp.utils.ValueUtils;

@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserMapper mapper;

	@Autowired
	ITaskService iTaskService;

	@Override
	public User create(User user) {
		validateUser(user);
		UserBean bean = mapper.map(user);
		bean.setCreatedAt(new Date());
		return mapper.map(userRepository.save(bean));
	}

	private void validateUser(User user) {
		if (user == null)
			throw ApiException.nullError("User object is required");
		ValueUtils.checkRequiredString(user.getName(), "User Name is required");
		ValueUtils.checkRequiredString(user.getEmail(), "User Email is required");
		ValueUtils.checkRequiredString(user.getUserRole(), "User Role is required");
		ValueUtils.checkRequired(user.getStatus(), "User status is required");
	}

	@Override
	public User find(Long id) {
		return mapper.map(findWithCheck(id));
	}

	@Override
	public List<Task> assigned(Long userId) {
		findWithCheck(userId);
		return iTaskService.assignedTasks(userId);
	}

	public UserBean findWithCheck(Long userId) {
		UserBean bean = userRepository.findById(userId)
				.orElseThrow(() -> ApiException.notFound("User does not exist with given id " + userId));
		return bean;
	}

}
