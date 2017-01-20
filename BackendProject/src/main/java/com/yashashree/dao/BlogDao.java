package com.yashashree.dao;

import java.util.List;

import com.yashashree.model.BlogComment;
import com.yashashree.model.BlogPost;
import com.yashashree.model.PROJ2_USER;

public interface BlogDao {
	List<BlogPost> getBlogPosts();
	BlogPost getBlogPost(int id);
	BlogPost addBlogPost(PROJ2_USER user,BlogPost blogPost);
	List<BlogComment> getComments(int blogId);
	BlogPost addBlogComment(PROJ2_USER user,BlogComment Comment);
	
	BlogPost updateBlog(int id,BlogPost blogPost);
	void deleteBlog(int id);
}
