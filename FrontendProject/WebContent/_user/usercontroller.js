app.controller('UserController',function($scope,$rootScope,$location,UserService){
	$scope.user={id:'',username:'',password:'',email:'',role:'',isOnline:'',enabled:''};
	$scope.message;
	$scope.submit=function(){
		console.log('Entering - submit function in usercontroller')
		UserService.authenticate($scope.user)
		.then(function(response){
			$scope.user=response.data;
			$rootScope.currentUser=$scope.user;
			console.log('currentUser in rootScope ' + $rootScope.currentUser.id)
			$location.path("/home");
	},
	function(response){//invalid user name and password - failure 
		 console.log('invalid username and password')
		  $scope.message="Invalid Username and Password";
		  $location.path("/login");
	})
}
	
//		.then(function(response){
//			console.log('Entering - authenticate function in usercontroller')
//
//			if(response.status==401)
//				{
//				  console.log('invalid username and password')
//				  $scope.message="Invalid Username and Password";
//				  $location.path("/login");
//				}
//			else{
//				$scope.user=response.data;
//				$rootScope.currentUser=$scope.user;
//				console.log('currentUser in rootScope ' + $rootScope.currentUser.id)
//				
//				$location.path("/home");
//			}
//		})
//	}	
	
	$scope.registerUser=function(){
		console.log('entering registerUser')
		UserService.registerUser($scope.user)
		.then(function(response){ //success 
			//response.data => user object
			console.log("registration success" + response.status)
			$scope.message="Registration successfull.. login using username and password.."
			$location.path("/login");
		},function(response){//failure
			console.log("registration failed" + response.status)
			//response.data => error object
			console.log(response.data)
			$scope.errorMessage="Registration failed...." + response.data.message
			$location.path("/register")
		})
	}
	

	$rootScope.logout=function(){
		console.log('logout function1')
		delete $rootScope.currentUser;
		console.log('logout function2')
		UserService.logout()
		.then(function(response){
			console.log("logged out successfully..");
			$scope.message="Logged out Successfully";
			$location.path('/login')
		},
		function(response){
			console.log('logout function3')
			console.log(response.status);
		})
		
	}
	
	$rootScope.hasRole=function(role){
		if($rootScope.currentUser.role==undefined)
			return false;
		return $rootScope.currentUser.role==role;
	}
	

})