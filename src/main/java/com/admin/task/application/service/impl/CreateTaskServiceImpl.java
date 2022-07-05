package com.admin.task.application.service.impl;

import com.admin.task.application.repository.TaskModelRepository;
import com.admin.task.application.service.CreateTaskService;
import com.admin.task.domain.model.entity.Task;
import com.admin.task.domain.model.TaskStatus;
import com.admin.task.domain.request.dto.CreateTaskRequestDto;
import com.admin.task.domain.request.entity.UploadTaskInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateTaskServiceImpl
        implements CreateTaskService {

    private final JavaMailSender emailSender;
    private final TaskModelRepository taskModelRepository;

    @Override
    public String createTask(final CreateTaskRequestDto dto,
                             final Model model)  {

        String task_number = generateTaskNumber();
        taskModelRepository.save(new Task(
                task_number,
                dto.getProduct(),
                dto.getName(),
                dto.getSurname(),
                dto.getFather_name(),
                dto.getMob(),
                dto.getEmail(),
                dto.getCheck(),
                dto.getDetail_problem(),
                generateDateTimeString(),
                TaskStatus.NEW.getValue())
        );
        try {
            //sendEmailMessage(task_number, dto.getProduct(), dto.getEmail());
            log.info(">> Message with task {} was send successfully <<", task_number);
        } catch (Exception ex) {
            throw new IllegalStateException("Error sending email! The message was not sent: ", ex);
        }
            model.addAttribute("task_number", task_number);

        return "add-task-result";
    }

    @Override
    public String openCreatedTask(final String Id,
                                  Model model) {
        Iterable<Task> taskModel = taskModelRepository.findByTask_number(Id);
        model.addAttribute("taskModel", taskModel);
        model.addAttribute("title", model.getAttribute("task_number"));

        return "open-task";
    }

    private String generateTaskNumber() {
        String unique = UUID.randomUUID().toString();
        String uniqueID = unique.replaceAll("[^0-9]", "");
        return uniqueID.substring((uniqueID.length() - 8));
    }

    private String generateDateTimeString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    @Async
    public void sendEmailMessage(final String taskNumber,
                                  final String product,
                                  final String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(product);
        message.setText(("""
                По Вашему обращению принята заявка #%s.
                Ожидайте, пожалуйста, в течение 3 дней с Вами свяжется специалист.
                С уважением, команда GG-Bank""").formatted(taskNumber));

        this.emailSender.send(message);
    }
}
