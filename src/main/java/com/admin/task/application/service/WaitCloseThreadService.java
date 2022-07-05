package com.admin.task.application.service;

import com.admin.task.application.repository.TaskModelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
@Slf4j
public class WaitCloseThreadService extends Thread {

    private final TaskModelRepository taskModelRepository;
    private final String taskNumber;

    public WaitCloseThreadService(TaskModelRepository taskModelRepository,
                                  String taskNumber) {
        this.taskModelRepository = taskModelRepository;
        this.taskNumber = taskNumber;
    }

    @Override
    public void run() {
        try {
            String taskNumber = this.taskNumber;
            log.info(">> task {} done. Waiting to close...", taskNumber);
            WaitCloseThreadService.sleep(60000);
            taskModelRepository.UpdateTaskClose(taskNumber);
            log.info(">> task {} closed...", taskNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
