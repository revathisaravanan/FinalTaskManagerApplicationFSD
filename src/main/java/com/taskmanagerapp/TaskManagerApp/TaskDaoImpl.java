package com.taskmanagerapp.TaskManagerApp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository("TaskDao")
public class TaskDaoImpl implements TaskDao{


       /*@Autowired

       private static Configuration cfg;

       @Autowired

       private static SessionFactory sessonFactory;

       @Autowired

       private static ThreadLocal hibernateHolder = new ThreadLocal();

       @Autowired

       private EntityManagerFactory entityManagerFactory;

       private CriteriaBuilder builder = entityManagerFactory.getCriteriaBuilder();



       public SessionFactory getSessionFactory() {

              return sessonFactory;

       }*/

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    /* (non-Javadoc)
     * @see com.fsd.taskmanager.TaskDao#addOrMergeTask(com.fsd.taskmanager.Task)
     */
    public void addOrMergeTask(Task task) {
        /*Session session = getSession();*/
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        /*CriteriaBuilder builder = session.getCriteriaBuilder();*/
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(task);
            //session.flush();
            //session.clear();
        }catch (RuntimeException e) {
            //tx.rollback();
            session.close();
            throw new RuntimeException("Exception occured in addOrMergeTask",e);
        }finally{
            tx.commit();
        }
    }

    /* (non-Javadoc)
     * @see com.fsd.taskmanager.TaskDao#deleteTask(com.fsd.taskmanager.Task)
     */
    public void deleteTask(Task task) {

        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        Transaction tx = session.beginTransaction();

        try{
            //Task getTask =  new Task();
            //Task task1 = (Task)session.load(Task.class, new Integer("2"));
            //Serializable id = task.getTaskId();
            session.load(task, task.getTaskId());
            session.delete(task);
        	  /*Query query = session.createQuery("delete Task as task where task.taskId = :taskId");
        	  query.setParameter("taskId", task.getTaskId());

        	  int result = query.executeUpdate();*/

            //session.flush();

            //session.clear();
        }catch (Exception e) {
            //tx.rollback();
            session.close();
            throw new RuntimeException("Exception occured in deleteTask",e);
        }finally{
            tx.commit();
        }
    }

    /* (non-Javadoc)
     * @see com.fsd.taskmanager.TaskDao#getAllTask()
     */
    public List<Task> getAllTask() {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        List<Task> taskList = null;
        try{
            taskList = session.createCriteria(Task.class).list();
        }catch (Exception e) {
            throw new RuntimeException("Exception occured in getAllTask",e);
        }
        return taskList;
    }

 /*      public void updateTask(Task task) {

           Session session = getSession();
 	   Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

           Transaction tx = session.beginTransaction();

           try{

                  session.saveOrUpdate(task);

           //session.flush();

           //session.clear();

           }catch (RuntimeException e) {

                  //tx.rollback();

                  session.close();

                  throw new RuntimeException("Exception occured in addTask",e);

           }finally{

                  tx.commit();



           }

    }


       public List<Task> getTaskbyTaskName(String taskName) {

    	   Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();

           CriteriaBuilder builder = session.getCriteriaBuilder();

           CriteriaQuery<Task> criteria = builder.createQuery(Task.class );

           Root<Task> root = criteria.from(Task.class);

           List<Task> taskList = null;

           try{

         	  Query q =  session.createQuery(

                       "from Task as task where " +

                       " task.taskName = :taskName ");
         	  	q.setParameter("taskName",taskName);

         	  	taskList = q.list();
        	Query query = session.createQuery("from  " + Task.class.getName() + " where taskName = :taskName ");
       	    query.setParameter("taskName", taskName);
       	    taskList = query.list();

           }catch (Exception e) {



                  System.out.println("Exception occured in addTask " +e);

           }

		return taskList;
       }*/

       /*public boolean isExistingTask(String taskName) {

              Session session = getSession();

              Transaction tx = session.beginTransaction();

              CriteriaQuery<Task> criteria = builder.createQuery(Task.class );

              Root<Task> root = criteria.from(Task.class);



              try{

            	  Query q =  session.createQuery(

                                  "from Task as task where " +

                                  " task.taskName = :taskName ");
            	  q.setParameter("taskname",taskName);

            	  List<Task> taskList = q.list();

                     criteria.select(root).where(builder.equal(root.get("taskName"), taskName));

                     Query q = session.createQuery(criteria);

                     Task task = q.getSingleResult();

                     if(taskList == null){
                           return false;

                     }

              }catch (Exception e) {



                     System.out.println("Exception occured in addTask " +e);

              }

              return true;

       }



       public List<Task> getTaskbyTaskName(String taskName) {

              Session session = getSession();

              Transaction tx = session.beginTransaction();

              CriteriaQuery<Task> criteria = builder.createQuery(Task.class );

              Root<Task> root = criteria.from(Task.class);

              List<Task> taskList = null;

              try{

            	  Query q =  session.createQuery(

                          "from Task as task where " +

                          " task.taskName = :taskName ");
            	  	q.setParameter("taskname",taskName);

            	  	taskList = q.list();

              }catch (Exception e) {



                     System.out.println("Exception occured in addTask " +e);

              }

              return taskList;

       }



       public List<Task> getTasksByParentTask(String parentTaskName) {

              Session session = getSession();

              Transaction tx = session.beginTransaction();

              CriteriaQuery<Task> criteria = builder.createQuery(Task.class );

              Root<Task> root = criteria.from(Task.class);

              List<Task> taskList = null;

              try{

            	  Query q =  session.createQuery(

                          "from Task as task where " +

                          " task.parentaskName.parentaskName = :parentTaskName ");
            	  	q.setParameter("taskname",parentTaskName);

            	  	taskList = q.list();

              }catch (Exception e) {



                     System.out.println("Exception occured in addTask " +e);

              }

              return task;

    	   return null;

       }



       public List<Task> getTasksByPriority(int from, int to) {

              // TODO Auto-generated method stub

              return null;

       }



       public List<Task> getTasksByDate(Date startDate, Date endDate) {

              // TODO Auto-generated method stub

              return null;

       }



       public void updateTask(Task task) {

              // TODO Auto-generated method stub



       }



       public Session getSession() {

              Session session = (Session) hibernateHolder.get();

              if(session ==null){

                     try{

                     session = sessonFactory.openSession();

                     hibernateHolder.set(session);

                     }catch(HibernateException e){

                           throw new HibernateException("Could not open Hibernate session", e);

                     }



              }

              return session;

       }





       public void configure() {

              cfg.addAnnotatedClass(com.fsd.taskmanager.Task.class);

              cfg.addAnnotatedClass(com.fsd.taskmanager.Task.class);

              sessonFactory = cfg.buildSessionFactory();



       }

 */

    public Task getTaskByID(int taskId) {

        Task task = new Task();
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        session.load(task, taskId);

        return task;

    }

 /*

       public ParentTask getParentTaskByID(int id) {

              // TODO Auto-generated method stub

              return null;

       }



       public ParentTask getParentTaskbyName(String ParenTaskName) {

              // TODO Auto-generated method stub

              return null;

       }*/
}
