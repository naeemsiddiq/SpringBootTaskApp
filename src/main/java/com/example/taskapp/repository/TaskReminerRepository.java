package com.example.taskapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskapp.bean.TaskReminderBean;

@Repository
public interface TaskReminerRepository extends JpaRepository<TaskReminderBean, Long> {

	List<TaskReminderBean> findAllByUserId(Long userId);

	List<TaskReminderBean> findAllByTaskIdAndUserId(Long taskId, Long userId);

}
