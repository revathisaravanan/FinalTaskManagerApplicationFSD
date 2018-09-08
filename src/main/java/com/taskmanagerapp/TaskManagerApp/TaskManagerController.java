package com.taskmanagerapp.TaskManagerApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(value = "/tasks")
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
        //@RequestMapping(value = "/taskManagercreateTask", method = RequestMethod.POST, headers = "Accept=application/json")
        //@PostMapping
        @RequestMapping(method = RequestMethod.POST)
        public String createTask(@RequestBody TaskManagerJSON taskManageJSON) {
        //public String createTask(@RequestBody Task task) throws ParseException{
            System.out.println("Im here inside create task" + taskManageJSON.getTaskName());
            //Create task and parent task entity object
            Task task = new Task();
            //Commented by Revathi
            //ParentTask parentTask = new ParentTask();


            //setting the JSON object to the the task and parent task bean
           task.setTaskName(taskManageJSON.getTaskName());
           task.setParentTask(taskManageJSON.getParentTaskName());

            //Commented by Revathitask.setParentTask(parentTask);
            //Commented by Revathi task.getParentTask().setParentaskName(taskManageJSON.getParentTask());

    	/*SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    	java.util.Date date = sdf1.parse(taskManageJSON.getStartDate());
    	java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());

    	java.util.Date enddate = sdf1.parse(taskManageJSON.getEndDate());
    	java.sql.Date sqlEndDate = new java.sql.Date(enddate.getTime());*/

            //Get the sql date from the util date object
            java.sql.Date sqlStartDate = new java.sql.Date(taskManageJSON.getStartDate().getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(taskManageJSON.getEndDate().getTime());
            task.setStartDate(sqlStartDate);
            task.setEndDate(sqlEndDate);
            task.setPriority(taskManageJSON.getPriority());
            task.setStatus("Active");
            //Call the add task method
            System.out.println("inside controller********"+ task.getTaskId()+"TaskName****"+task.getTaskName()+"TaskPriority***"+task.getPriority()
            +"TAskStartDate ****"+task.getStartDate()+"TAsk EndDate ***" + task.getEndDate() + "ParentTaskName..." + task.getParentTask());
            status = taskManageService.addTask(task);
            System.out.println("After Addition in table......" + status);
            return status;

        }

    /**
     * This method is to derive the list of task
     * @return taskManageJSONList
     */
    // @RequestMapping(value = "/getAllTask", method = RequestMethod.GET, headers = "Accept=application/json")
    @RequestMapping(method = RequestMethod.GET)
     public List<TaskManagerJSON> getTaskList(){
         //Create taskManagerJSON list object
         List<TaskManagerJSON> taskManageJSONList = new ArrayList<TaskManagerJSON>();
         List<Task> listOfTask = taskManageService.getTaskList();

         for (Task task: listOfTask){
             TaskManagerJSON taskManageJSON = new TaskManagerJSON();
             taskManageJSON.setTaskId(task.getTaskId());
             taskManageJSON.setTaskName(task.getTaskName());
             taskManageJSON.setPriority(task.getPriority());
             taskManageJSON.setStartDate(task.getStartDate());
             taskManageJSON.setEndDate(task.getEndDate());
             taskManageJSON.setParentTaskName(task.getParentTask());
             taskManageJSON.setStatus(task.getStatus());
             System.out.println("From Database....." + taskManageJSON.getTaskId() + "taskname..." +taskManageJSON.getTaskName()
                     + "priority...." +taskManageJSON.getPriority() + "startdate|||" +taskManageJSON.getStartDate()
                     +"enddate#####" + taskManageJSON.getEndDate() + "Parenttask *** " + taskManageJSON.getParentTaskName());

     /* if(task.getParentTask() != null) {
     taskManageJSON.setParentId(task.getParentTask().getParentId());
     taskManageJSON.setParentTask(task.getParentTask().getParentaskName());
     }*/
             taskManageJSONList.add(taskManageJSON);
         }
         //model.addAttribute("task", new TaskManagerJSON());
         //model.addAttribute("listOfTasks", taskManageJSONList);
         return taskManageJSONList;
     }

    @RequestMapping(method = RequestMethod.PUT)
    public String updateTask(@RequestBody TaskManagerJSON taskManageJSON) {
        System.out.println("update task");
        Task task = new Task();
        task.setTaskId(taskManageJSON.getTaskId());
        task.setTaskName(taskManageJSON.getTaskName());
        task.setParentTask(taskManageJSON.getParentTaskName());
        //Get the sql date from the util date object
        java.sql.Date sqlStartDate = new java.sql.Date(taskManageJSON.getStartDate().getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(taskManageJSON.getEndDate().getTime());
        task.setStartDate(sqlStartDate);
        task.setEndDate(sqlEndDate);
        task.setPriority(taskManageJSON.getPriority());
        if (taskManageJSON.getEndTask() != null && taskManageJSON.getEndTask().equalsIgnoreCase("yes")) {
            task.setStatus("InActive");
        }
        else {
            task.setStatus("Active");
        }
        status = taskManageService.updateTask(task);
        System.out.println("After updating in table......" + status);
        return status;
    }


    @RequestMapping(value = "/endTaskManager/{taskId}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public String endTask(@PathVariable int taskId) {

        /*Task task = new Task();
        System.out.println("Inside End MEthod......"+taskManageJSON.getTaskId());
        task.setTaskId(taskManageJSON.getTaskId());
        task.setStatus("InActive");
        task.setTaskName(taskManageJSON.getTaskName());
        task.setParentTask(taskManageJSON.getParentTaskName());
        //Get the sql date from the util date object
        java.sql.Date sqlStartDate = new java.sql.Date(taskManageJSON.getStartDate().getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(taskManageJSON.getEndDate().getTime());
        task.setStartDate(sqlStartDate);
        task.setEndDate(sqlEndDate);
        task.setPriority(taskManageJSON.getPriority());*/
        Task task = new Task();
        status = taskManageService.endTask(task);
        System.out.println("After Ending.........");
        //taskManageService.deleteTask(taskId);
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
        /*** Revathi@GetMapping
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
                //Commented by Revathi
               /* if(task.getParentTask() != null) {
                    taskManageJSON.setParentId(task.getParentTask().getParentId());
                    taskManageJSON.setParentTask(task.getParentTask().getParentaskName());
                }*/
                /*taskManageJSONList.add(taskManageJSON);
            }
            return taskManageJSONList;
        }

        @PutMapping
        public String updateTask(@RequestBody TaskManagerJSON taskManageJSON) throws ParseException {

            //Create task and parent task entity object
            Task task = new Task();
           // ParentTask parentTask = new ParentTask();

            //setting the JSON object to the the task and parent task bean
            task.setTaskName(taskManageJSON.getTask());
            task.setTaskId(taskManageJSON.getTaskId());
            //Commented by Revathi
            /*task.setParentTask(parentTask);
            task.getParentTask().setParentaskName(taskManageJSON.getParentTask());
            task.getParentTask().setParentId(taskManageJSON.getParentId());*/

    	/*SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    	java.util.Date date = sdf1.parse(taskManageJSON.getStartDate());
    	java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());

    	java.util.Date enddate = sdf1.parse(taskManageJSON.getEndDate());
    	java.sql.Date sqlEndDate = new java.sql.Date(enddate.getTime());*/

           /* java.sql.Date sqlStartDate = new java.sql.Date(taskManageJSON.getStartDate().getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(taskManageJSON.getStartDate().getTime());
            task.setStartDate(sqlStartDate);
            task.setEndDate(sqlEndDate);
            task.setPriority(taskManageJSON.getPriority());

            status = taskManageService.updateTask(task);

            return status;

        }

        */


}
