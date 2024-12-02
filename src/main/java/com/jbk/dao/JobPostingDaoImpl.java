package com.jbk.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.entities.JobPosting;
import com.jbk.entities.Student;

@Repository
public class JobPostingDaoImpl implements JobPostingDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int createJob(JobPosting jobPosting) {
        try {
            Session session = sessionFactory.openSession();

            JobPosting dbJobPosting = session.get(JobPosting.class, jobPosting.getId());

            if (dbJobPosting == null) {
                session.save(jobPosting);
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

	@Override
	public List<JobPosting> getAllJobs() {
		
		 try (Session session = sessionFactory.openSession()) {
	            String hql = "FROM JobPosting";
	            return session.createQuery(hql, JobPosting.class).list();
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	}

	@Override
	public int deleteJob(Long jobId) {
		try {
            Session session = sessionFactory.openSession();
            JobPosting dbjob = session.get(JobPosting.class, jobId);

            if (dbjob != null) {
                session.beginTransaction();
                session.delete(dbjob);
                session.getTransaction().commit();
                return 1;
            } else {
                return 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 3;
        }
	}

	@Override
    public int updateJobPosting(JobPosting jobPosting) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            JobPosting existingJobPosting = session.get(JobPosting.class, jobPosting.getId());
            if (existingJobPosting != null) {
               
                existingJobPosting.setJobTitle(jobPosting.getJobTitle());
                existingJobPosting.setCompanyName(jobPosting.getCompanyName());
                existingJobPosting.setJobDescription(jobPosting.getJobDescription());
                existingJobPosting.setJobLocation(jobPosting.getJobLocation());
                existingJobPosting.setEligibilityCriteria(jobPosting.getEligibilityCriteria());

               
                transaction.commit();
                return 1; 
            } else {
                return 2; 
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return 3; 
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
	
	
	
		
    
}
