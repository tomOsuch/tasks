package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void testGetAllTask() {
        //Given
        List<Task> tasks = List.of(new Task(1L, "Test_task", "test_task_content"));
        when(taskRepository.findAll()).thenReturn(tasks);
        //When
        List<Task> resultAllTask = dbService.getAllTasks();
        //Then
        assertEquals(1, resultAllTask.size());
    }

    @Test
    public void testGetTaskById() {
        //Given
        List<Task> tasks = List.of(new Task(1L, "Test_task", "test_task_content"));
        when(taskRepository.findById(tasks.get(0).getId())).thenReturn(Optional.ofNullable(tasks.get(0)));
        //When
        boolean resultFindById = dbService.getTaskById(1L).isPresent();
        //Then
        assertTrue(resultFindById);
    }
}
