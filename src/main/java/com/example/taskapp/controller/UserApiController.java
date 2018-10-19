package com.example.taskapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskapp.IConstants;
import com.example.taskapp.dto.Task;
import com.example.taskapp.dto.User;
import com.example.taskapp.repository.UserRepository;
import com.example.taskapp.service.IUserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(IConstants.API_URL + "/user")
public class UserApiController {

	@Autowired
	IUserService iUserService;
	
	@Autowired
	UserRepository userRepository;

	@PostMapping("/")
	@ApiOperation(value = "Create a user")
	public User create(@Valid @RequestBody User user) {
		return iUserService.create(user);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Find User")
	public User find(@PathVariable(value = "id") Long id) {
//		userRepository.findByIdAndNameContainingOrEmailContaining(1l,"abc","admin@task.com");
		return iUserService.find(id);
	}

	@GetMapping("/{userId}/assignedTasks") // /assignedTasks or /assigned/tasks/
	@ApiOperation(value = "Get tasks that are assigned to a user")
	public List<Task> assigned(@PathVariable(value = "userId") Long userId) {
		return iUserService.assigned(userId);
	}

}
