app.factory('UserService',function($http){
	var userService=this;
	var BASE_URL="http://localhost:8086/BackendProject"
		
	userService.authenticate=function(user){
		console.log('Entering - submit function in userservice')

		return $http.post(BASE_URL + "/login",user);
	}
	
	userService.registerUser=function(user){
		return $http.post(BASE_URL + "/register",user) 
	}
	
	userService.logout=function(){
		console.log('Entering - logout function in userservice')
		return $http.put(BASE_URL + "/logout") 
	}
	
	return userService;
})
