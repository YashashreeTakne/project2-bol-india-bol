var app=angular.module("myApp",['ngRoute'])
app.config(function($routeProvider){
	console.log('entering configuration')
	$routeProvider
	.when('/login',{
		controller:'UserController',
		templateUrl:'_user/login.html'
	})
	.when('/home',{
		templateUrl:'_home/home.html'
	})
	.when('/register',{
		controller:'UserController',
		templateUrl:'_user/register.html'
	})
		
	.when('/postJob',{
		controller:'JobController',
		templateUrl:'_job/createJob.html'
	})
	.when('/getAllJobs',{
		controller:'JobController',  // write a function to get all jobs from the backend => JobService
		templateUrl:'_job/jobs.html'  // to display the job titles in html page
	})
	
//	.when('/viewjobapply',{
//	controller:'JobController',
//	templateUrl:'__job/viewjobapply.html'
//})

//	.when('/getJobById/(jobId)',{
//		controller:'JobController',  // write a function to get all jobs from the backend => JobService
//		templateUrl:'_job/jobdetail.html'  // to display the job titles in html page
//	})
})