app.factory('BlogService',function($http){
	var BASE_URL="http://localhost:8086/BackendProject";
	var blogService=this;
	blogService.addPost=function(blogPost){
		console.log('addpost in service')
		return $http.post("http://localhost:8086/BackendProject/blog",blogPost);
	}
	
	blogService.getBlogPosts=function(){
		console.log('getBlogposts in service')
		return $http.get("http://localhost:8086/BackendProject/blog/list")
	};
	blogService.getBlogDetail=function(id){
		console.log('getBlogDetails')
		return $http.get("http://localhost:8086/BackendProject/blog/get/"+ id)
	};
	
	blogService.addComment=function(comment){
		return $http.post("http://localhost:8086/BackendProject/blog/comment",comment)
	};
	blogService.getComments=function(blogId){
		console.log('getcomments -- service')
		return $http.get("http://localhost:8086/BackendProject/blog/getcomments/"+blogId)
	}	;
	
//	
//	blogService.getBlog=function(id){
//		return $http.get(BASE_URL + "/blog/" + id)
//	};
blogService.deleteBlog=function(id){
		
		console.log("entering delete person in service with id"+id);
		return $http.delete(BASE_URL + "/blog/get/"+id)
		.then(function(response){
			console.log(response.status)
					return response.status;
		},function(response){
			
			console.log(response.status)

		});

	};
	
	
	blogService.updateBlog=function(id,blog){
		console.log('update blog in service')
		console.log('blog id ' + id)
		return $http.put(BASE_URL + "/blog/get/"+id, blog);
	}

	return blogService;
})
