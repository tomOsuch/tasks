package com.crud.tasks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Task o podanym id nie istnieje")
public class TaskNotFountException extends RuntimeException {
}
