package com.admin.task.controllers;

import com.admin.task.repository.taskModelRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class WaitCloseThread extends Thread {

    @Autowired
    private final taskModelRepository taskMRepository;

     public WaitCloseThread(taskModelRepository taskMRepository){
         this.taskMRepository=taskMRepository;
     }

    @Override
    public void run() {


        try {
            String task_number=
            MainController.taskSave.get("task_number");
            WaitCloseThread.sleep(60000);
            taskMRepository.UpdateTaskClose(task_number);
        } catch (InterruptedException  e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
}
