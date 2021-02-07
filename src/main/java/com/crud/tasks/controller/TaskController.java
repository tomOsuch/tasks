package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.dto.TaskDto;
import com.crud.tasks.exception.TaskNotFoundException;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/task")
public class TaskController {

    private final DbService service;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskController(DbService service, TaskMapper taskMapper) {
        this.service = service;
        this.taskMapper = taskMapper;
    }

    @GetMapping(value = "getTasks")
    public List<TaskDto> getTasks() {
        List<Task> tasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }

    @GetMapping("/getTask/{id}")
    public TaskDto getTask(@PathVariable Long id) {
        return taskMapper.mapToTaskDto(service.getTaskById(id).orElseThrow(TaskNotFoundException::new));
    }

    @DeleteMapping("/deleteTask")
    public void deleteTask(@RequestParam Long id) {
        try {
            service.deleteTask(id);
        } catch (EmptyResultDataAccessException e) {
            throw new TaskNotFoundException();
        }
    }

    @PutMapping("/updateTask")
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        Task saveTask = service.saveTask(task);
        return taskMapper.mapToTaskDto(saveTask);
    }

    @PostMapping(value = "/createTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        service.saveTask(task);
    }
}
