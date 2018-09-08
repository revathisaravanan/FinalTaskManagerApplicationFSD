package com.taskmanagerapp.TaskManagerApp;

import java.util.List;

public interface TaskDao {

    void addOrUpdateTask(Task task);

    List<Task> getAllTask();

}
