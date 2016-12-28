package com.yashashree.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yashashree.dao.JobDao;
import com.yashashree.model.Job;
import com.yashashree.model.PROJ2_USER;
import com.yashashree.model.Error;


@RestController
public class JobController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
private JobDao jobDao;
    @RequestMapping(value="/postJob",method=RequestMethod.POST)
	public ResponseEntity<?> postJob(@RequestBody Job job,HttpSession session){// we r using httpsession object for authentication
		PROJ2_USER user=(PROJ2_USER)session.getAttribute("user");
		// this method returns a object user
		
		if(user==null){// user is null when user tries to execute this method without login 
			System.out.println("hello");
			Error error=new Error(1,"Unauthorized user.. login using valid credentials");
			System.out.println("hello1");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401
			// it returns response object with data{it contains an error object} and status401
			// in frontend we can get error msg by response.data.message
		}
		else{
			System.out.println("hello2");
			logger.debug(user.getUsername());//NullPointerException is user is null
			System.out.println(" Role of User " + user.getRole());
	                job.setPostedOn(new Date());
	    			System.out.println("hello3");
					jobDao.postJob(job);
					System.out.println("hello4");
				return new ResponseEntity<Void>(HttpStatus.OK);
			
	}
	// backend will return response object =[data + status code]
		// for success ==> [void,200] {and from jobcontroller.js return to home.html}
		// for failure ==> [error,401] {and from jobcontroller.js return to login.html} -- for unauthorized 
		//            else [void,500] {and from jobcontroller.js return to same postJob.html}--for server side error/other exceptions
}
    @RequestMapping(value="/getAllJobs",method=RequestMethod.GET)
    public ResponseEntity<?> getAllJobs(HttpSession session){
    	PROJ2_USER user=(PROJ2_USER)session.getAttribute("user");
    	if(user==null){
    		System.out.println("USER is null");
    		Error error=new Error(1,"Unauthorized user.. login using valid credentials");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401
    	}
    	System.out.println("USER OBJECT " + user.getRole());
    	List<Job> jobs=jobDao.getAllJobs();
    	return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);    	
    	//response 
    }
    
//    @RequestMapping(value="/getJobById/{jobId}",method=RequestMethod.GET)
//    public ResponseEntity<?> getJobById(@PathVariable(value="jobId")int jobId,HttpSession session){
//    	PROJ2_USER user=(PROJ2_USER)session.getAttribute("user");
//    	System.out.println("hello31");
//    	if(user==null){
//    		System.out.println("hello32");
//    		System.out.println("USER is null");
//    		Error error=new Error(1,"Unauthorized user.. login using valid credentials");
//    		System.out.println("hello33");
//			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401
//    	}
////    	
////    	System.out.println("USER OBJECT " + user.getRole());
////    	List<Job> jobs1=jobDao.getAllJobs();
////    	return new ResponseEntity<List<Job>>(jobs1,HttpStatus.OK);    	
//    	System.out.println("USER OBJECT " + user.getRole());
//    	logger.debug("JobId "+ jobId);
//    	System.out.println("hello34");
//    	Job jobs1=jobDao.getJobById(jobId);
//    	System.out.println("hello35");
//    	return new ResponseEntity<Job>(jobs1,HttpStatus.OK);
//    	//response 
//    }
 
}