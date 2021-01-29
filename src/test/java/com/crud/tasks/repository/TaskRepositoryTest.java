package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testTaskFindAll() {
        //Given
        Task task = new Task("Test_task", "test_task_content");
        taskRepository.save(task);
        Long id = task.getId();
        //When
        List<Task> resultTaskList = taskRepository.findAll();
        //Then
        assertEquals(26, resultTaskList.size());
        //CleanUp
        taskRepository.deleteById(id);
    }

    @Test
    public void testTaskFindById() {
        //Given
        Task task = new Task("Test_task", "test_task_content");
        taskRepository.save(task);
        Long id = task.getId();
        //When
        Optional<Task> resultFindTask = taskRepository.findById(id);
        //Then
        assertTrue(resultFindTask.isPresent());
        //CleanUp
        taskRepository.deleteById(id);
    }

    @Test
    public void testTaskDelete() {
        //Given
        Task task = new Task("Test_task", "test_task_content");
        taskRepository.save(task);
        Long id = task.getId();
        //When
        taskRepository.deleteById(id);
        Optional<Task> taskFind = taskRepository.findById(id);
        //Then
        assertFalse(taskFind.isPresent());
    }
}
