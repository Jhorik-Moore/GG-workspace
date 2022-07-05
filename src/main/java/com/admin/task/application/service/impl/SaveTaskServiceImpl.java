package com.admin.task.application.service.impl;

import com.admin.task.application.repository.TaskModelRepository;
import com.admin.task.application.service.SaveTaskService;
import com.admin.task.application.service.WaitCloseThreadService;
import com.admin.task.domain.model.TaskStatus;
import com.admin.task.domain.request.dto.SaveTaskRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaveTaskServiceImpl
        implements SaveTaskService {

    private final TaskModelRepository taskModelRepository;
    private final JavaMailSender javaMailSender;

    @Override
    public String taskSave(SaveTaskRequestDto dto,
                           Model model) {
        try {
            taskModelRepository.UpdateTask(
                    dto.getTask_number(),
                    dto.getStatus(),
                    dto.getHard(),
                    dto.getCategory(),
                    dto.getBusiness(),
                    dto.getCode_close(),
                    dto.getSolution(),
                    dto.getRemark()
            );
            model.addAttribute("answer_text", SAVE_CHANGES_MESSAGE);
        } catch (Exception ex){
            throw new IllegalStateException("Error saving task : " + ex);
        }
        if ((dto.getStatus().equals(TaskStatus.DONE.getValue()) && dto.getButton().equals("Email"))) {
            try {
                //sendEmailMessageDone(dto);
                startRegalementTimeToCloseTask(dto.getTask_number());
                model.addAttribute("answer_text", EMAIL_MESSAGE_DONE);
                log.info(">> Done! Message with answer task {} was send successfully <<", dto.getTask_number());
            } catch (Exception ex) {
                throw new IllegalStateException("Error sending email! The message was not sent: ", ex);
            }

        } else if (dto.getStatus().equals(TaskStatus.DONE.getValue()) && dto.getButton().equals("Save")) {
            taskModelRepository.UpdateRemark(dto.getTask_number(), REMARK_CONTACT_CLIENT);
        }
        model.addAttribute("title", SAVE_CHANGES_TITLE);

        return "open-task-save";
    }

    private void startRegalementTimeToCloseTask(final String taskNumber) {
        WaitCloseThreadService waitClose =
                new WaitCloseThreadService(taskModelRepository, taskNumber);
        waitClose.start();
    }

    private void sendEmailMessageDone(SaveTaskRequestDto dto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(dto.getEmail());
        message.setSubject(dto.getProduct());
        message.setText(("""
                Уважаемый(ая) %s
                ********************
                По Вашей заявке # %2$s принято решение:
                %3$s
                ********************
                С уважением, команда GG-Bank!""").formatted(
                        dto.getFio(),
                dto.getTask_number(),
                dto.getSolution()));

        this.javaMailSender.send(message);

    }
}
