package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private final static String SUBJECT = "Tasks: New Trello card";
    private final SimpleEmailService simpleEmailService;
    private final TaskRepository taskRepository;
    private final AdminConfig adminConfig;

    //@Scheduled(cron = "0 0 10 * * *")
    @Scheduled(fixedDelay = 10000)
    public void sendInformationEmail() {

        simpleEmailService.sendDaily(
                new Mail(
                        adminConfig.getAdminMail(),
                        null,
                        SUBJECT,
                        messageTasksCount(taskRepository.count())
                )
        );
    }

    private String messageTasksCount(long size) {
        return String.format("Currently in database you got:", size == 1 ? size + " task" : size + " tasks");
    }
}
