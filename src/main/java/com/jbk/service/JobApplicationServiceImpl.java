package com.jbk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbk.dao.JobApplicationDao;
import com.jbk.entities.JobApplication;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    @Autowired
    JobApplicationDao jobApplicationDao;

    @Override
    public int createJobApplication(JobApplication jobApplication) {
        return jobApplicationDao.createJobApplication(jobApplication);
    }
}
