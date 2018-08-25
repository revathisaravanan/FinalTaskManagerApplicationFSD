package com.taskmanagerapp.TaskManagerApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TaskManagerService")

public class TaskManagerServiceImpl implements TaskManagerService {

    @Autowired
    TaskDao taskDaoImpl;

    /* (non-Javadoc)
     * @see com.fsd.taskmanager.TaskManagerService#addTask(com.fsd.taskmanager.Task)
     */
    public String addTask(Task task) {
        taskDaoImpl.addOrMergeTask(task);
        return "Added";
    }

    /* (non-Javadoc)
     * @see com.fsd.taskmanager.TaskManagerService#updateTask(com.fsd.taskmanager.Task)
     */
    public String updateTask(Task task) {
        taskDaoImpl.addOrMergeTask(task);
        return "Updated";
    }

    /*public List<Task> retrieveTask(Task task) {
    	List<Task> taskList = null;
    	taskList = taskDaoImpl1.getTaskbyTaskName(task.getTaskName());
        return taskList;
    }*/

    /* (non-Javadoc)
     * @see com.fsd.taskmanager.TaskManagerService#getTaskList()
     */
    public List<Task> getTaskList() {
        List<Task> taskList = null;
        taskList = taskDaoImpl.getAllTask();
        return taskList;
    }

    /* (non-Javadoc)
     * @see com.fsd.taskmanager.TaskManagerService#deleteTask(com.fsd.taskmanager.Task)
     */
    public String deleteTask(Task task) {
        taskDaoImpl.deleteTask(task);
        return "Deleted";
    }
}
