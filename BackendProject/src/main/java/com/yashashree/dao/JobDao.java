package com.yashashree.dao;

import java.util.List;

import com.yashashree.model.Job;

public interface JobDao {
void postJob(Job job);
List<Job> getAllJobs();
}
