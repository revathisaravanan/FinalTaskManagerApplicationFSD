package com.taskmanagerapp.TaskManagerApp;

import java.util.List;

public interface TaskManagerService {
    String addOrUpdateTask(Task task);
    List<Task> getTaskList();
}
