package com.yashashree.dao;

import java.util.List;

import com.yashashree.model.Job;

public interface JobDao {
void postJob(Job job);
List<Job> getAllJobs();
//Job getJobById(int jobId);

//List<Job> getJobById(int jobId);
// if we keep it like this we r needed to type cast it in backend controller so changed to Job type here itself

}
