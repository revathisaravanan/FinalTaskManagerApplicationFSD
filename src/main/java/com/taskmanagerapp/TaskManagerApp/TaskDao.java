package com.taskmanagerapp.TaskManagerApp;

import java.util.List;

public interface TaskDao {

    void addOrUpdateTask(TaskData task);

    List<TaskData> getAllTask();

}
