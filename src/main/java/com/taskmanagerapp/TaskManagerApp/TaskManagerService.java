package com.taskmanagerapp.TaskManagerApp;

import java.util.List;

public interface TaskManagerService {
    String addTask(Task task);
    String updateTask(Task task);
    List<Task> getTaskList();
    String deleteTask(Task task);
}
