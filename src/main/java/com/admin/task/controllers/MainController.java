package com.admin.task.controllers;
import com.admin.task.model.Task;
import com.admin.task.repository.taskModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MainController {
    private String taskNumberID;

    @Autowired
    private taskModelRepository taskMRepository;

    @Autowired
    public JavaMailSender emailSender;


    Map<String,String> map = new HashMap<>();
    Map<String,String> taskAdd = new HashMap<>();
    Map<String,String> taskSearch = new HashMap<>();
    static Map<String,String> taskSave = new HashMap<>();
    Map<String,String> answer = new HashMap<>();


    public MainController() throws IOException {
    }

    @GetMapping("/gg-bank.ua")
    public String open_client(Model model) {
        return "site-form";
    }

    //ОТКРЫВАЕМ ГЛАВНУЮ СТРАНИЦУ

    @GetMapping("/workspace.task.ua")
    public String home(Model model) {
        model.addAttribute("title", "Работа над заявкой");
        return "home";
    }

    //Редактирование заявки *************************************************************************************

    @GetMapping("/workspace.task.ua/edit_task")
    public String NR(Model model) {
        model.addAttribute("title", "Редактирование заявки");
        return "edit-task";
    }

    //Принимаем переменные от странницы редактирования заявки *************************************************

    @PostMapping("/workspace.task.ua/edit_task")
    public String edit_task(@RequestParam String task_numbers, @RequestParam String business,
                            @RequestParam String category, @RequestParam String code_close,
                            @RequestParam String status, @RequestParam String hard,
                            Model model) {
        map.put("task_number",task_numbers);
        map.put("bus",business);
        map.put("cat",category);
        map.put("stat",status);
        map.put("codecl",code_close);
        map.put("hard",hard);

        if(task_numbers.length()>10) {
            String[] arrayList;
            arrayList = task_numbers.split(",");
            for(int i = 0; i<arrayList.length; i++) {
                String task_number = arrayList[i];
                taskMRepository.UpdateCloseTask(task_number,status,hard,category,business,
                        code_close);
            }
        } else {
            String task_number = task_numbers;
            taskMRepository.UpdateCloseTask(task_number,status,hard,category,business,
                    code_close);
        }
        return "redirect:/workspace.task.ua/edit_task/answer";
    }

   //Показываем успешность редактирования заявки **********************************************************************

    @GetMapping("/workspace.task.ua/edit_task/answer")
    public String edit_taskAnswer(Model model) {
        for (Map.Entry<String, String> el : map.entrySet()) {
                    if (el.getKey().equals("task_number")) {
                        model.addAttribute("task_number", el.getValue());
                    }if (el.getKey().equals("bus")) {
                        model.addAttribute("task_bus", el.getValue());
                    }if (el.getKey().equals("cat")) {
                        model.addAttribute("task_cat", el.getValue());
                    }if (el.getKey().equals("stat")) {
                        model.addAttribute("task_stat", el.getValue());
                    }if (el.getKey().equals("codecl")) {
                        model.addAttribute("task_codecl", el.getValue());
                    }if (el.getKey().equals("hard")) {
                        model.addAttribute("task_hard", el.getValue());
                    }
                 }
          model.addAttribute("title", "Результат редактирования");
          return "edit-task-result";
        }

    //Открываем страницу просмотра отредактированной заявки *****************************************************************

    @GetMapping("/workspace.task.ua/edit_task/answer/task_result")
    public String task_result(Model model) {
        for (Map.Entry<String, String> el : map.entrySet()) {
            if (el.getKey().equals("task_number")) {
                String task_value = el.getValue();
                if(task_value.length()>10) {
                   String[] arrayList;
                      arrayList=  task_value.split(",");
                      taskNumberID= arrayList[arrayList.length - 1];
                        Iterable<Task> taskModel = taskMRepository.findByTask_number(taskNumberID);
                        model.addAttribute("taskModel", taskModel);
                        model.addAttribute("title", "Просмотр заявки");

                } else {
                    taskNumberID=map.get("task_number");
                    Iterable<Task> taskModel = taskMRepository.findByTask_number(taskNumberID);
                    model.addAttribute("taskModel", taskModel);
                    model.addAttribute("title", "Просмотр заявки");
                }
            }
        }

        return "open-task";
    }

    //Открываем страницу создания заявки *****************************************************************************

    @GetMapping("/workspace.task.ua/send_task")
       public String task_add(Model model) {
        model.addAttribute("title","Создание заявки");
        return "task-add";
    }

    //Получаем переменные полей создания заявки ********************************************************************

    @PostMapping("/workspace.task.ua/send_task")
    public String send_task(@RequestParam String product, @RequestParam String detail_problem,
                            @RequestParam String surname, @RequestParam String name,
                            @RequestParam String father_name, @RequestParam String mob,
                            @RequestParam String email, @RequestParam String check,
                            Model model) {

        String unique = UUID.randomUUID().toString();
          String uniqueID = unique.replaceAll("[^0-9]", "");
            String task_number = uniqueID.substring((uniqueID.length()-8));
             taskAdd.put("task_number",task_number);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         Date date = new Date();
         String date_time = dateFormat.format(date);

           String status = "Новая";

            Task regtask = new Task(task_number, product, name, surname,
                father_name, mob, email, check, detail_problem, date_time,status);
                   taskMRepository.save(regtask);


        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(product);
        message.setText("По Вашему обращению принята заявка #"+task_number+"."+"\r\n"+
                         "Ожидайте, пожалуйста, в течение 3 дней с Вами свяжется специалист."+"\r\n"+
                         "С уважением, команда GG-Bank");

        // Send Message!
        this.emailSender.send(message);

        if(check.equals("телефон")||check.equals("viber")||
                check.equals("email")||check.equals("telegram")){
            model.addAttribute("task_number",task_number);
            return "form-answer";
        }

        return   "redirect:/workspace.task.ua/send_task/add_result";
    }


    //Показываем страницу успешности подачи заявки ***********************************************************************

    @GetMapping("/workspace.task.ua/send_task/add_result")
    public String send_task(Model model) {
        String taskNumberID = taskAdd.get("task_number");
        model.addAttribute("task_number", taskNumberID);
        model.addAttribute("title", "Результат подачи заявки");
        return "add-task-result";
    }


     // Открываем поданную заявку ****************************************************************************************

    @GetMapping("/workspace.task.ua/open_task")
    public String open_task(Model model) {
        taskNumberID = taskAdd.get("task_number");
        Iterable<Task> taskModel = taskMRepository.findByTask_number(taskNumberID);
        model.addAttribute("taskModel", taskModel);
        model.addAttribute("title", taskNumberID);
        return "open-task";
    }

    //Поиск заявки **************************************************************************************************
    @GetMapping("/workspace.task.ua/search_task")
    public String search_task(Model model) {
        return "search-task";
    }
    //Принимаем параметры поиска заявки ******************************************************************************
    @PostMapping("/workspace.task.ua/search_task")
    public String search_task(@RequestParam String filter, String search_param,
                            Model model) {
        map.put("filter",filter);
        map.put("search_param",search_param);
        return "redirect:/workspace.task.ua/search_task/result";
    }


    // Результат Поиска заявки ******************************************************************************************
    @GetMapping("/workspace.task.ua/search_task/result")
    public String search_task_result(Model model) {
        String search_param = map.get("search_param");
        String filter = map.get("filter");
        String wrong ="Нет записей по указанным параметрам...";

        if(filter.equals("task_number")) {
            taskNumberID = search_param;
            List<Task> taskModel = taskMRepository.findByTask_number(taskNumberID);
            int rows = taskModel.size();
            model.addAttribute("rows", rows);
            if(taskModel.isEmpty()){
                model.addAttribute("Result", wrong);
            } else {
                int rows1 = taskModel.size();
                model.addAttribute("rows", rows1);
                model.addAttribute("taskModel", taskModel);
            }
        }
        else if(filter.equals("phone")) {
            String phone = search_param;
            List<Task> taskModel = taskMRepository.findByPhone(phone);
            if(taskModel.isEmpty()){
                model.addAttribute("Result", wrong);
            } else {
                int rows2 = taskModel.size();
                model.addAttribute("rows", rows2);
                model.addAttribute("taskModel", taskModel);
            }
        }
        else if(filter.equals("email")) {
            String email = search_param;
            List<Task> taskModel = taskMRepository.findByEmail(email);
            if(taskModel.isEmpty()){
                model.addAttribute("Result", wrong);
            } else {
                int rows3 = taskModel.size();
                model.addAttribute("rows", rows3);
                model.addAttribute("taskModel", taskModel);
            }
        }

        return "search-task-result";
    }

    @GetMapping("/workspace.task.ua/search_task/update")
    public String search_task_update(Model model ) {
        List<Task> desckModel = taskMRepository.findNewTask();
        System.out.println(desckModel.toString());
        int rows1 = desckModel.size();
        model.addAttribute("rows", rows1);
        model.addAttribute("taskModel", desckModel);
        return "search-task-result";
    }
    //Открываем выбранную из поиска заявку *******************************************************************************
    @GetMapping("/workspace.task.ua/search_task/result/open_task")
    public String open_task_fromSearch(@RequestParam String Id,Model model ) {
        String taskNumberID = Id;
        Iterable<Task> taskModel = taskMRepository.findByTask_number(taskNumberID);
        for(Task task: taskModel){
            String st =task.getStatus();
              if(st.equals("Закрыта")){
                  System.out.println(" Заявка "+taskNumberID+" закрыта");
                  model.addAttribute("taskModel", taskModel);
                  model.addAttribute("title", taskNumberID);
                  return "open-closed-task";
              }
        }
        model.addAttribute("taskModel", taskModel);
        model.addAttribute("title", taskNumberID);
        return "open-task";
    }

    //Сохраняем изменния ***********************************************************************************************
    @PostMapping("/workspace.task.ua/task_save")
    public String open_task_save(@RequestParam String status, @RequestParam String hard,
                                 @RequestParam String category, @RequestParam String business,
                                 @RequestParam String code_close, @RequestParam String solution,
                                 @RequestParam String remark, @RequestParam String task_number,
                                 @RequestParam String fio, @RequestParam String email,
                                 @RequestParam String button,@RequestParam String product, Model model){
        taskMRepository.UpdateTask(task_number,status,hard,category,business,
                                   code_close,solution,remark);
        answer.put("answer","Изменения сохранены успешно!");

        if((status.equals("Выполнена")||status.equals("Выполнено")) && button.equals("Email")){
            taskSave.put("task_number",task_number);
            WaitCloseThread waitClose = new WaitCloseThread(taskMRepository);
            waitClose.start();
            answer.put("answer","Ответ успешно отправлен на Email");
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject(product);
            message.setText("Уважаемый(ая) "+fio+"!"+"\r\n"+
                    "********************"+"\r\n"+
                    "По Вашей заявке #"+task_number+" принято решение:"+"\r\n"+
                    solution+"."+"\r\n"+
                    "********************"+"\r\n"+
                    "С уважением, команда GG-Bank");
            this.emailSender.send(message);
        }
        else if (status.equals("Выполнена") && button.equals("Save")){
            remark = "Коммуникация не проведена со статусом заявки 'Выполнена'. Предоставьте ответ инициатору";
            taskMRepository.UpdateRemark(task_number,remark);

        }

        return "redirect:/workspace.task.ua/task_save";
    }

     @GetMapping("/workspace.task.ua/task_save")
        public String open_task_save(Model model){
        String answer_text = answer.get("answer");
        model.addAttribute("answer_text",answer_text);
        model.addAttribute("title","Сохранение изменений");
         return "open-task-save";
     }


}



