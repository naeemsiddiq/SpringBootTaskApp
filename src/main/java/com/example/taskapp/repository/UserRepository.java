package com.example.taskapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.taskapp.bean.UserBean;

@Repository
public interface UserRepository extends JpaRepository<UserBean, Long> {

	UserBean findByName(String name);

	UserBean findByUserRole(String userRole);
	
	UserBean findByIdAndNameContainingOrEmailContaining(Long id, String name, String email);

}
