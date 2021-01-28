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
import static org.mockito.Mockito.*;

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

    @Test
    public void testSaveTask() {
        //Given
        Task task = new Task(1L, "Test_task", "test_task_content");
        when(taskRepository.save(task)).thenReturn(task);
        //When
        Task resultTask = dbService.saveTask(task);
        //Then
        assertEquals(task.getId(), resultTask.getId());
        assertEquals(task.getTitle(), resultTask.getTitle());
        assertEquals(task.getContent(), resultTask.getContent());
    }

    @Test
    public void testDeleteById() {
        //Given
        Task task = new Task(1L, "Test_task", "Test_content");
        Long id = task.getId();
        //When
        dbService.deleteTask(id);
        //Then
        verify(taskRepository, times(1)).deleteById(id);
    }
}
