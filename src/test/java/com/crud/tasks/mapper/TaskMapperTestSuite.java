package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.dto.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaskMapperTestSuite {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testTaskMapToTaskDtoList() {
        //Given
        List<Task> tasks = List.of(new Task(1L, "task_title", "task_content"));
        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(tasks);
        //Then
        assertEquals(tasks.get(0).getTitle(), taskDtoList.get(0).getTitle());
        assertEquals(tasks.get(0).getId(), taskDtoList.get(0).getId());
        assertEquals(tasks.get(0).getContent(), taskDtoList.get(0).getContent());
        assertEquals(tasks.size(), taskDtoList.size());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "task_title", "task_content");
        //When
        TaskDto resultTaskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(task.getContent(), resultTaskDto.getContent());
        assertEquals(task.getId(), resultTaskDto.getId());
        assertEquals(task.getTitle(), resultTaskDto.getTitle());
    }

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "taskDto_title", "taskDto_content");
        //When
        Task resultTask = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(taskDto.getId(), resultTask.getId());
        assertEquals(taskDto.getContent(), resultTask.getContent());
        assertEquals(taskDto.getTitle(), resultTask.getTitle());
    }
}
