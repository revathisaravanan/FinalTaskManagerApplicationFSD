package com.taskmanagerapp.TaskManagerApp;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude
public class TaskManagerJSON {

    private int taskId;
    private int ParentId;
    private String task;
    private String parentTask;
    //@JsonFormat(shape=JsonFormat.Shape.OBJECT, pattern="yyyy-MM-dd")
    private Date startDate;
    //@JsonFormat(shape=JsonFormat.Shape.OBJECT, pattern="yyyy-MM-dd")
    private Date endDate;
    private int priority;

    public int getTaskId() {
        return taskId;
    }
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
    public int getParentId() {
        return ParentId;
    }
    public void setParentId(int parentId) {
        ParentId = parentId;
    }
    public String getTask() {
        return task;
    }
    public void setTask(String task) {
        this.task = task;
    }
    public String getParentTask() {
        return parentTask;
    }
    public void setParentTask(String parentTask) {
        this.parentTask = parentTask;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
}
