package com.example.taskapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskapp.bean.TaskBean;

@Repository
public interface TaskRepository extends JpaRepository<TaskBean, Long> {

	List<TaskBean> findAllByAssignedToId(Long userId);
	
}
