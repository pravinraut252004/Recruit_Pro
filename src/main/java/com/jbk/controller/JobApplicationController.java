package com.jbk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.entities.JobApplication;
import com.jbk.service.JobApplicationService;

@RestController
@RequestMapping("/job-application")
public class JobApplicationController {

    @Autowired
    JobApplicationService jobApplicationService;

    @PostMapping("/create-application")
    public String createJobApplication(@RequestBody JobApplication jobApplication) {
        int status = jobApplicationService.createJobApplication(jobApplication);

        switch (status) {
            case 1:
                return "Job Application Submitted Successfully";
            case 2:
                return "Job Application Already Exists";
            case 3:
                return "Something went Wrong";
            default:
                return "";
        }
    }
    
    
    
}
