package com.jbk.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.entities.JobApplication;

@Repository
public class JobApplicationDaoImpl implements JobApplicationDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int createJobApplication(JobApplication jobApplication) {
        try {
            Session session = sessionFactory.openSession();
            
            JobApplication dbJobApplication = session.get(JobApplication.class, jobApplication.getId());

            if (dbJobApplication == null) {
                session.save(jobApplication);
                session.beginTransaction().commit();
                return 1; 
            } else {
                return 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 3; 
        }
    }
}
