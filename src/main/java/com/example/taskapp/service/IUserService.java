package com.example.taskapp.service;

import java.util.List;

import com.example.taskapp.bean.UserBean;
import com.example.taskapp.dto.Task;
import com.example.taskapp.dto.User;

public interface IUserService {

	User create(User user);

	User find(Long id);

	List<Task> assigned(Long userId);

	UserBean findWithCheck(Long userId);

}
