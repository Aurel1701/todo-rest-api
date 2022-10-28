package com.soprasteria.todo.Repository;

import org.springframework.data.repository.CrudRepository;

import com.soprasteria.todo.Model.Task;

public interface TaskRepository extends CrudRepository<Task, Integer> {

}
