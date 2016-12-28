package com.yashashree.daoimpl;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yashashree.dao.JobDao;
import com.yashashree.model.Job;

@Repository
public class JobDaoImpl implements JobDao {
	@Autowired
private SessionFactory sessionFactory;
	
	@Override
	public void postJob(Job job) {
		Session session=sessionFactory.openSession();
		session.save(job);
		session.flush();
		session.close();

	}

	@Override
	public List<Job> getAllJobs() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Job");
		List<Job> jobs=query.list();
		session.close();
		return jobs;
	}

//	@Override
//	public Job getJobById(int jobId) {
//		Session session=sessionFactory.openSession();
//		System.out.println("hello36");
//		Job jobs1=(Job)session.get(Job.class, jobId);
//		System.out.println("hello37");
//		session.close();
//		System.out.println("hello38");
//		return jobs1;
//	}

}