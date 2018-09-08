package com.taskmanagerapp.TaskManagerApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(value = "/tasks")
public class TaskManagerController {

    @Autowired
    private TaskManagerServiceImpl taskManageService;

    private String status = null;

    @RequestMapping(method = RequestMethod.POST)
    public String createTask(@RequestBody TaskManagerJSON taskJSON) {
        Task task = this.updateTaskFromTaskJSON(taskJSON);
        task.setStatus("Active");
        status = taskManageService.addOrUpdateTask(task);
        return status;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<TaskManagerJSON> getTaskList(){
        List<TaskManagerJSON> taskManageJSONList = new ArrayList<TaskManagerJSON>();
        List<Task> listOfTask = taskManageService.getTaskList();

        for (Task task: listOfTask){
            TaskManagerJSON taskManageJSON = new TaskManagerJSON();
            taskManageJSON.setTaskId(task.getTaskId());
            taskManageJSON.setTaskName(task.getTaskName());
            taskManageJSON.setPriority(task.getPriority());
            taskManageJSON.setStartDate(task.getStartDate());
            taskManageJSON.setEndDate(task.getEndDate());
            taskManageJSON.setParentTaskName(task.getParentTask());
            taskManageJSON.setStatus(task.getStatus());
            taskManageJSONList.add(taskManageJSON);
        }
        return taskManageJSONList;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String updateTask(@RequestBody TaskManagerJSON taskJSON) {
        System.out.println("update task");
        Task task = this.updateTaskFromTaskJSON(taskJSON);
        if (taskJSON.getEndTask() != null && taskJSON.getEndTask().equalsIgnoreCase("yes")) {
            task.setStatus("InActive");
        }
        else {
            task.setStatus("Active");
        }
        status = taskManageService.addOrUpdateTask(task);
        return status;
    }

    private Task updateTaskFromTaskJSON(TaskManagerJSON taskJSON) {
        Task task = new Task();
        task.setTaskId(taskJSON.getTaskId());
        task.setTaskName(taskJSON.getTaskName());
        task.setParentTask(taskJSON.getParentTaskName());
        //Get the sql date from the util date object
        java.sql.Date sqlStartDate = new java.sql.Date(taskJSON.getStartDate().getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(taskJSON.getEndDate().getTime());
        task.setStartDate(sqlStartDate);
        task.setEndDate(sqlEndDate);
        task.setPriority(taskJSON.getPriority());
        return task;
    }
}