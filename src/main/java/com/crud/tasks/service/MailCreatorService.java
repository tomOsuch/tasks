package com.crud.tasks.service;

import com.crud.tasks.client.TrelloClient;
import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private TrelloClient trelloClient;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());

        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildDailyEmail(String message) {
        Context context = new Context();
        context.setVariable("admin_config", adminConfig);
        context.setVariable("goodbye_message", "Dziękuję, miłego dnia");
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("daily_update", trelloClient.getTrelloBoards().size());

        return templateEngine.process("mail/daily-update-mail", context);
    }
}
