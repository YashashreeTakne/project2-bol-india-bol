package com.yashashree.daoimpl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yashashree.dao.BlogDao;
import com.yashashree.model.BlogComment;
import com.yashashree.model.BlogPost;
import com.yashashree.model.PROJ2_USER;

@Repository
public class BlogDaoImpl implements BlogDao {
	@Autowired
private SessionFactory sessionFactory;
	@Override
	public List<BlogPost> getBlogPosts() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from BlogPost");
		List<BlogPost> blogPosts=query.list();
		session.close();
		return blogPosts;
	}

	@Override
	public BlogPost getBlogPost(int id) {
		Session session=sessionFactory.openSession();
		BlogPost blogPost=(BlogPost)session.get(BlogPost.class, id);
		session.close();
		return blogPost;
	}

	@Override
	public BlogPost addBlogPost(PROJ2_USER user, BlogPost blogPost) {
		Session session=sessionFactory.openSession();
		blogPost.setCreatedBy(user);
		blogPost.setCreatedOn(new Date());
		session.save(blogPost);
		session.flush();
		BlogPost addedBlogPost=(BlogPost)session.get(BlogPost.class, blogPost.getId());
		return addedBlogPost;
	}

	@Override
	public BlogPost addBlogComment(PROJ2_USER user, BlogComment blogComment) {
		Session session=sessionFactory.openSession();
	 blogComment.setCreatedBy(user);
	 blogComment.setCreatedOn(new Date());
	 BlogPost blogPost=(BlogPost)session.get(BlogPost.class, blogComment.getBlogPost().getId());
			 blogComment.setBlogPost(blogPost);
	 session.merge(blogComment);
	 session.flush();
	 session.close();
	 return blogPost;
	 
	}

	@Override
	public List<BlogComment> getComments(int blogId) {
		Session session=sessionFactory.openSession();
		BlogPost blogPost=(BlogPost)session.get(BlogPost.class, blogId);
		List<BlogComment> blogComments=blogPost.getComments();
		session.close();
		return blogComments;
	}
}