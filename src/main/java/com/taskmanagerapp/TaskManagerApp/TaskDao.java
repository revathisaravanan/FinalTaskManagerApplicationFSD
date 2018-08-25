package com.taskmanagerapp.TaskManagerApp;

import java.util.List;

public interface TaskDao {

    /**
     * This method is to add or merge the task from DB
     * @param task
     */
    void addOrMergeTask(Task task);

    /**
     * This method is to delete the task  from DB
     * @param task
     */
    void deleteTask(Task task);

    /**
     * This method is to get the list of task from DB
     * @return
     */
    List<Task> getAllTask();

           /*
            *
            Task getTaskByID(int taskId);

            void updateTask(Task task);

            List<Task> getTaskbyTaskName(String taskName);

            public ParentTask getParentTaskByID(int id);

                ParentTask getParentTaskbyName(String ParenTaskName);

                boolean isExistingTask(String taskName);

                List<Task> getTaskbyTaskName(String taskName);

                List<Task> getTasksByParentTask(String parentTaskName);

                List<Task> getTasksByPriority(int from, int to);

                List<Task> getTasksByDate(Date startDate, Date endDate);

                void updateTask(Task task);

                public void configure();

                public SessionFactory getSessionFactory();

                public Session getSession();*/
}
