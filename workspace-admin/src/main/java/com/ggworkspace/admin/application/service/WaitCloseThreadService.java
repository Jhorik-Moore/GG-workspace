package com.ggworkspace.admin.application.service;

import com.ggworkspace.admin.application.repository.TaskModelRepository;
import lombok.extern.slf4j.Slf4j;

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
