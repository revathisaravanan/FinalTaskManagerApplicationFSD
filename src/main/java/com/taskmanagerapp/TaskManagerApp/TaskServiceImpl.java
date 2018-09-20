package com.taskmanagerapp.TaskManagerApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TaskManagerService")

public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskDao taskDaoImpl;

    public String addOrUpdateTask(TaskData taskData) {
        taskDaoImpl.addOrUpdateTask(taskData);
        return "Added";
    }

    public List<TaskData> getTaskList() {
        List<TaskData> taskList;
        taskList = taskDaoImpl.getAllTask();
        return taskList;
    }

}