package com.taskmanagerapp.TaskManagerApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders="*")
@RestController
@RequestMapping({"/taskManager"})

public class TaskManagerController {


        @Autowired
        private TaskManagerServiceImpl taskManageService;

        private String status = null;

        /**
         * This method is to create the task
         * @param taskManageJSON
         * @return
         * @throws ParseException
         */
        @PostMapping
        public String createTask(@RequestBody TaskManagerJSON taskManageJSON) throws ParseException{

            //Create task and parent task entity object
            Task task = new Task();
            ParentTask parentTask = new ParentTask();


            //setting the JSON object to the the task and parent task bean
            task.setTaskName(taskManageJSON.getTask());

            task.setParentTask(parentTask);
            task.getParentTask().setParentaskName(taskManageJSON.getParentTask());

    	/*SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    	java.util.Date date = sdf1.parse(taskManageJSON.getStartDate());
    	java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());

    	java.util.Date enddate = sdf1.parse(taskManageJSON.getEndDate());
    	java.sql.Date sqlEndDate = new java.sql.Date(enddate.getTime());*/

            //Get the sql date from the util date object
            java.sql.Date sqlStartDate = new java.sql.Date(taskManageJSON.getStartDate().getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(taskManageJSON.getStartDate().getTime());
            task.setStartDate(sqlStartDate);
            task.setEndDate(sqlEndDate);
            task.setPriority(taskManageJSON.getPriority());
            //Call the add task method
            status = taskManageService.addTask(task);

            return status;

        }

    /*@GetMapping(path = {"/{taskNm}"})
    public TaskManagerJSON retrieveTask(String taskNm){

    	Task task = new Task();

    	TaskManagerJSON taskManageJSON = new TaskManagerJSON();
    	task.setTaskName(taskNm);

    	List<Task> listOfTask = taskManageService.retrieveTask(task);

    	for (Task task1: listOfTask){
    		taskManageJSON.setTask(task1.getTaskName());
    		taskManageJSON.setPriority(task1.getPriority());
    		if(task1.getParentTask() != null)
    		taskManageJSON.setParentTask(task1.getParentTask().getParentaskName());
    		taskManageJSON.setStartDate(task1.getStartDate());
    		taskManageJSON.setEndDate(task1.getEndDate());
    		taskManageJSON.setParentId(task1.getParentTask().getParentId());
    		taskManageJSON.setTaskId(task1.getTaskId());
    	}
		return taskManageJSON;

    }*/

        /**
         * This method is to derive the list of task
         * @return taskManageJSONList
         */
        @GetMapping
        public List<TaskManagerJSON> getTaskList(){
            //Create taskManagerJSON list object
            List<TaskManagerJSON> taskManageJSONList = new ArrayList<TaskManagerJSON>();
            List<Task> listOfTask = taskManageService.getTaskList();

            for (Task task: listOfTask){
                TaskManagerJSON taskManageJSON = new TaskManagerJSON();
                taskManageJSON.setTaskId(task.getTaskId());
                taskManageJSON.setTask(task.getTaskName());
                taskManageJSON.setPriority(task.getPriority());
                taskManageJSON.setStartDate(task.getStartDate());
                taskManageJSON.setEndDate(task.getEndDate());
                if(task.getParentTask() != null) {
                    taskManageJSON.setParentId(task.getParentTask().getParentId());
                    taskManageJSON.setParentTask(task.getParentTask().getParentaskName());
                }
                taskManageJSONList.add(taskManageJSON);
            }
            return taskManageJSONList;
        }

        @PutMapping
        public String updateTask(@RequestBody TaskManagerJSON taskManageJSON) throws ParseException {

            //Create task and parent task entity object
            Task task = new Task();
            ParentTask parentTask = new ParentTask();

            //setting the JSON object to the the task and parent task bean
            task.setTaskName(taskManageJSON.getTask());
            task.setTaskId(taskManageJSON.getTaskId());
            task.setParentTask(parentTask);
            task.getParentTask().setParentaskName(taskManageJSON.getParentTask());
            task.getParentTask().setParentId(taskManageJSON.getParentId());

    	/*SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    	java.util.Date date = sdf1.parse(taskManageJSON.getStartDate());
    	java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());

    	java.util.Date enddate = sdf1.parse(taskManageJSON.getEndDate());
    	java.sql.Date sqlEndDate = new java.sql.Date(enddate.getTime());*/

            java.sql.Date sqlStartDate = new java.sql.Date(taskManageJSON.getStartDate().getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(taskManageJSON.getStartDate().getTime());
            task.setStartDate(sqlStartDate);
            task.setEndDate(sqlEndDate);
            task.setPriority(taskManageJSON.getPriority());

            status = taskManageService.updateTask(task);

            return status;

        }

        @DeleteMapping(path ={"/{taskId}"})
        public String deleteTask(int taskId) {
            Task task = new Task();
            task.setTaskId(taskId);

            status = taskManageService.deleteTask(task);
            //taskManageService.deleteTask(taskId);
            return status;
        }


}
