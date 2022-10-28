package com.soprasteria.todo.Controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soprasteria.todo.Model.Task;
import com.soprasteria.todo.Repository.TaskRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class TaskController {

	@Autowired
	private TaskRepository taskRepository;
	
	@GetMapping("/todo")
	public List<Task> getAllTasks(){
		return (List<Task>) taskRepository.findAll();
	}
	
	@GetMapping("/todo/{taskId}")
	public Task getTaskById(@PathVariable(value= "taskId") int taskId) {
		Optional<Task> t=taskRepository.findById(taskId);
		if(t.isPresent())
			return t.get();
		else
			return new Task();
	}
	
	
	@PostMapping("/createtask") 
	public Task createTask(@RequestBody Task task) {
		return taskRepository.save(task);
		
	}
	
	
	@DeleteMapping("/todo/delete/{taskId}")
	public void  deleteTaskById(@PathVariable(value = "taskId") int taskId) {
		taskRepository.deleteById(taskId);
	}
	
	
	@PutMapping("/todo/update/{taskId}") 
	public Task updateTask(
			@PathVariable (value = "taskId") int taskId,
			@RequestBody Task task) {
		Optional<Task> t=taskRepository.findById(taskId);
		if(t.isPresent()) {
			Task t1=t.get();
			t1.setTaskName(task.getTaskName());
			t1.setDone(task.isDone());
			return taskRepository.save(t1);
		} else
				return null;
			}
	
	@PutMapping("/todo/completed/{taskId}") //toggle
	public Task completedTask(
			@PathVariable (value = "taskId") int taskId) {
		Optional<Task> t=taskRepository.findById(taskId);
		if(t.isPresent()) {
			Task t1=t.get();
			t1.setDone(true);
			return taskRepository.save(t1);
		} else
				return null;
			}
	
}
