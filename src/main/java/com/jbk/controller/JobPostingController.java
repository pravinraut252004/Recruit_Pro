package com.jbk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.entities.JobPosting;
import com.jbk.entities.Student;
import com.jbk.service.JobPostingService;

@RestController
@RequestMapping("/job")
public class JobPostingController {

    @Autowired
    JobPostingService jobPostingService;

    @PostMapping("/create-job")
    public String createJob(@RequestBody JobPosting jobPosting) {
        int status = jobPostingService.createJob(jobPosting);

        switch (status) {
            case 1:
                return "Job Posting Added Successfully";
            case 2:
                return "Job Posting Already Exists";
            case 3:
                return "Something went Wrong";
            default:
                return "";
        }
    }
    
    
    @GetMapping("/job-posting")
    public Object allJobs() {
        List<JobPosting> jobs = jobPostingService.allJobs();

        if (!jobs.isEmpty()) {
            return jobs;
        } else {
            return "No job found.";
        }
    }
    
    @DeleteMapping("/deletejobposting")
    public String deleteJob(@RequestParam Long jobId) {
        int status = jobPostingService.deleteJob(jobId);

        switch (status) {
            case 1:
                return "Job Posting Deleted Successfully";
            case 2:
                return "Job Posting Not Found";
            case 3:
                return "Something went Wrong";
            default:
                return "Unknown status";
        }
    }
    
    
    @PutMapping("/update")
    public String updateJobPosting(@RequestBody JobPosting updatedJobPosting) {
        int status = jobPostingService.updateJobPosting(updatedJobPosting);

        switch (status) {
            case 1:
                return "JobPosting updated successfully.";
            case 2:
                return "JobPosting not found.";
            default:
                return "Something went wrong. Please try again later.";
        }
    }
    

    
   

}
