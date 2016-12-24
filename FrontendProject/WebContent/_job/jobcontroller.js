app.controller('JobController',function($scope,$location,JobService){
	$scope.job={jobTitle:'',jobDescription:'',skillsRequired:'',salary:'',location:''}
	// we can assign any function or variable to this $scope
	//$location will rediect to perticulr html page 
	$scope.jobs={}
	$scope.saveJob=function(){
		console.log('entering save job in job controller')
		JobService.saveJob($scope.job)
		.then(function(response){
			console.log("successfully inserted job details");
			alert("Posted job successfully");
			$location.path('/home');
		},function(response){
			console.log("failure " +response.status);
			if(response.ststus==401){
				$location.path('/login')
			}
			else{ 
//			console.log(response.data.message)
			$location.path('/postJob')
			}
		})
	}
	
	function getAllJobs(){
		console.log('entering get All jobs')
		JobService.getAllJobs()
		.then(function(response){
			console.log(response.status); //200
			$scope.jobs=response.data;  //List<Job>
			
		},function(response){
			console.log(response.status)
		})
	}
	getAllJobs();

})