package com.taskmanagerapp.TaskManagerApp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude
public class TaskManagerJSON {

    private int taskId;
    //private int ParentId;
    private String taskName;
    private String parentTaskName;
//    private String parentTask;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date endDate;
    private int priority;
    private String status;
    private String endTask;

    public int getTaskId() {
        return taskId;
    }
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
//    public int getParentId() {
//        return ParentId;
//    }
//    public void setParentId(int parentId) {
//        ParentId = parentId;
//    }
    //    public String getParentTask() {
//        return parentTask;
//    }
//    public void setParentTask(String parentTask) {
//        this.parentTask = parentTask;
//    }
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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getParentTaskName() {
        return parentTaskName;
    }

    public void setParentTaskName(String parentTaskName) {
        this.parentTaskName = parentTaskName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEndTask() {
        return endTask;
    }

    public void setEndTask(String endTask) {
        this.endTask = endTask;
    }
}
