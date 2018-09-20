package com.taskmanagerapp.TaskManagerApp;

import java.util.List;

public interface TaskService {
    String addOrUpdateTask(TaskData taskData);
    List<TaskData> getTaskList();
}
