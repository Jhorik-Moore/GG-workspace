package com.admin.task.adapter.controllers;

import com.admin.task.domain.model.entity.Task;
import com.admin.task.application.repository.TaskModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

/*Controller not used in app. Only for Postman usage*/
@Controller
public class ForPostmanController {

    @Autowired
    private TaskModelRepository taskMRepository;
    private final Map<String,Object> answer = new HashMap<>();

    public void putInAnswer(String status, String message, Object result){
            answer.put("status",status);
            answer.put("message",message);
            answer.put("result",result);
        }

    @GetMapping("/workspace.task.ua/tasks/{phone}")
    public ResponseEntity<?> findByPhone(@PathVariable(name = "phone") String phone) {
        List<Task> tasks = taskMRepository.findByPhone(phone);
        if (!tasks.isEmpty()) {
           putInAnswer("ok","success",tasks);
            return new ResponseEntity<>(answer, HttpStatus.OK);
        } else putInAnswer("error","Data not found!",phone);
            return new ResponseEntity<>(answer, HttpStatus.NOT_FOUND);

    }

    @GetMapping("/workspace.task.ua/task/{product}")
    public ResponseEntity<?> findByProduct(@PathVariable(name = "product") String product) {
        List<Task> tasks = taskMRepository.findByProduct(product);
        if (!tasks.isEmpty()) {
            putInAnswer("ok","success",tasks);
            return new ResponseEntity<>(answer, HttpStatus.OK);
        } else putInAnswer("error","Data not found!",product);
              return new ResponseEntity<>(answer,HttpStatus.NOT_FOUND);
    }

    @PostMapping("/workspace.task.ua/add_task")
    public ResponseEntity<?> add_task(@RequestBody Task payload){
        try {
            taskMRepository.save(payload);
            putInAnswer("ok","Data added successfully!",payload);
            return new ResponseEntity<>(answer,HttpStatus.OK);
        } catch (Exception e){
            putInAnswer("err",e.getMessage(),payload);
            return new ResponseEntity<>(answer,HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("/workspace.task.ua/delete/{phone}")
    public ResponseEntity<?> deleteByPhone(@PathVariable(name="phone") String phone){
        try {
            taskMRepository.deleteByPhone(phone);
            putInAnswer("ok","Data deleted successfully!",phone );
            return new ResponseEntity<>(answer,HttpStatus.OK);
        } catch (Exception e){
            putInAnswer("err",e.getMessage() ,phone);
            return new ResponseEntity<>(answer,HttpStatus.BAD_REQUEST);
        }
    }
}
