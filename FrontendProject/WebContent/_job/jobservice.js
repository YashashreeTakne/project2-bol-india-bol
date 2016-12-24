app.factory('JobService',function($http){
	var jobService={};
	var BASE_URL="http://localhost:8086/BackendProject"
	jobService.saveJob=function(job){
		return $http.post(BASE_URL + "/postJob" , job);
		// it returns response object
	}
	
	jobService.getAllJobs=function(){
		return $http.get(BASE_URL + "/getAllJobs");
	}
	
	return jobService;
	// it returns jobservice object to be used in controller further
})
	
//	return jobService;