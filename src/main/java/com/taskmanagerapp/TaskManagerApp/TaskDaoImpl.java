package com.taskmanagerapp.TaskManagerApp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("TaskDao")
public class TaskDaoImpl implements TaskDao{

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public void addOrUpdateTask(TaskData taskData) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(taskData);
        }catch (RuntimeException e) {
            session.close();
            throw new RuntimeException("Exception occured in addOrUpdateTask",e);
        }finally{
            tx.commit();
        }
    }

    public List<TaskData> getAllTask() {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        List<TaskData> taskList = null;
        try{
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<TaskData> query = criteriaBuilder.createQuery(TaskData.class);
            Root<TaskData> root = query.from(TaskData.class);
            query.select(root);
            taskList = session.createQuery(query).getResultList();

        }catch (Exception e) {
            throw new RuntimeException("Exception occured in getAllTask",e);
        }
        return taskList;
    }

}
