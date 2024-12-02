package com.jbk.dao;

import java.util.List;

import com.jbk.entities.JobPosting;

public interface JobPostingDao {
    public int createJob(JobPosting jobPosting);

	public List<JobPosting> getAllJobs();

	public int deleteJob(Long jobId);

	public int updateJobPosting(JobPosting jobPosting);

	
}
