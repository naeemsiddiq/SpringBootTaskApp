package com.example.taskapp.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.taskapp.bean.UserBean;
import com.example.taskapp.dto.User;

@Component
public class UserMapper extends AbstractMapper implements IUserMapper {

	@Override
	public User map(UserBean bean) {
		User dto = null;
		if (bean != null) {
			dto = new User();
			dto.setId(bean.getId());
			dto.setName(bean.getName());
			dto.setEmail(bean.getEmail());
			dto.setUserRole(bean.getUserRole());
			dto.setCreatedBy(bean.getCreatedBy());
			dto.setCreatedAt(bean.getCreatedAt());
			dto.setUpdatedBy(bean.getUpdatedBy());
			dto.setUpdatedAt(bean.getUpdatedAt());
			dto.setStatus(bean.getStatus());
		}
		return dto;
	}

	@Override
	public UserBean map(User dto) {
		UserBean bean = null;
		if (dto != null) {
			bean = new UserBean();
			bean.setId(dto.getId());
			bean.setName(dto.getName());
			bean.setEmail(dto.getEmail());
			bean.setStatus(dto.getStatus());
			bean.setUserRole(dto.getUserRole());
		}
		return bean;
	}

	@Override
	public List<User> map(List<UserBean> beans) {
		// TODO Auto-generated method stub
		return null;
	}

}
