package com.taskmanagerapp.TaskManagerApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(value = "/tasks")
public class TaskController {

    @Autowired
    private TaskServiceImpl taskManageService;

    private String status = null;

    @RequestMapping(method = RequestMethod.POST)
    public String createTask(@RequestBody TaskJSONData taskJSONData) {
        TaskData taskData = this.updateTaskFromTaskJSON(taskJSONData);
        taskData.setStatus("Active");
        status = taskManageService.addOrUpdateTask(taskData);
        return status;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<TaskJSONData> getTaskList(){
        List<TaskJSONData> taskManageJSONList = new ArrayList<TaskJSONData>();
        List<TaskData> listOfTask = taskManageService.getTaskList();

        for (TaskData taskData: listOfTask){
            TaskJSONData taskJSONData = new TaskJSONData();
            taskJSONData.setTaskId(taskData.getTaskId());
            taskJSONData.setTaskName(taskData.getTaskName());
            taskJSONData.setPriority(taskData.getPriority());
            taskJSONData.setStartDate(taskData.getStartDate());
            taskJSONData.setEndDate(taskData.getEndDate());
            taskJSONData.setParentTaskName(taskData.getParentTask());
            taskJSONData.setStatus(taskData.getStatus());
            taskManageJSONList.add(taskJSONData);
        }
        return taskManageJSONList;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String updateTask(@RequestBody TaskJSONData taskJSONData) {
        System.out.println("update task");
        TaskData taskData = this.updateTaskFromTaskJSON(taskJSONData);
        if (taskJSONData.getEndTask() != null && taskJSONData.getEndTask().equalsIgnoreCase("yes")) {
            taskData.setStatus("InActive");
        }
        else {
            taskData.setStatus("Active");
        }
        status = taskManageService.addOrUpdateTask(taskData);
        return status;
    }

    private TaskData updateTaskFromTaskJSON(TaskJSONData taskJSONData) {
        TaskData taskData = new TaskData();
        taskData.setTaskId(taskJSONData.getTaskId());
        taskData.setTaskName(taskJSONData.getTaskName());
        taskData.setParentTask(taskJSONData.getParentTaskName());
        java.sql.Date sqlStartDate = new java.sql.Date(taskJSONData.getStartDate().getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(taskJSONData.getEndDate().getTime());
        taskData.setStartDate(sqlStartDate);
        taskData.setEndDate(sqlEndDate);
        taskData.setPriority(taskJSONData.getPriority());
        return taskData;
    }
}