package com.jbk.service;

import java.util.List;

import com.jbk.entities.JobPosting;

public interface JobPostingService {
    public int createJob(JobPosting jobPosting);

	public List<JobPosting> allJobs();

	public int deleteJob(Long jobId);

	int updateJobPosting(JobPosting jobPosting);

	
	
	

	
}
