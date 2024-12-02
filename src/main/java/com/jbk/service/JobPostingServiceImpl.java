package com.jbk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbk.dao.JobPostingDao;
import com.jbk.entities.JobPosting;
import com.jbk.entities.Student;

@Service
public class JobPostingServiceImpl implements JobPostingService {

    @Autowired
    JobPostingDao jobPostingDao;

    @Override
    public int createJob(JobPosting jobPosting) {
        return jobPostingDao.createJob(jobPosting);
    }

	@Override
	public List<JobPosting> allJobs() {
		
		return jobPostingDao.getAllJobs();
	}
	

	@Override
	public int deleteJob(Long jobId) {
		
		return jobPostingDao.deleteJob(jobId);
	}

	@Override
	public int updateJobPosting(JobPosting jobPosting) {
		return jobPostingDao.updateJobPosting(jobPosting);
		
	}
	
	
	

   
}
