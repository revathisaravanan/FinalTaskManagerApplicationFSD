package com.taskmanagerapp.TaskManagerApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TaskManagerService")

public class TaskManagerServiceImpl implements TaskManagerService {

    @Autowired
    TaskDao taskDaoImpl;

    public String addOrUpdateTask(Task task) {
        taskDaoImpl.addOrUpdateTask(task);
        return "Added";
    }

    public List<Task> getTaskList() {
        List<Task> taskList;
        taskList = taskDaoImpl.getAllTask();
        return taskList;
    }

}