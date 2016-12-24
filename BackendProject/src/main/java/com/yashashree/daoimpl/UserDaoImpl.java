package com.yashashree.daoimpl;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yashashree.dao.UserDao;
import com.yashashree.model.PROJ2_USER;
import com.yashashree.model.User;
@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
//	public PROJ2_USER authenticate(PROJ2_USER prol_user) {
//		System.out.println("h9");
//
//		Session session=sessionFactory.openSession();
//		System.out.println("h10");
//		System.out.println("name"+prol_user.getUsername()+"password"+prol_user.getPassword());
//
//		List<PROJ2_USER> validUser = session.createQuery("from PROJ2_USER where username='"+prol_user.getUsername()+"'  and password='"+prol_user.getPassword()+"'").list();
//			if(validUser!=null && !validUser.isEmpty()){
//				System.out.println("Hellow123");
//				return validUser.get(0);
//		}
//			return null;
//	}
	
	
	
	@Override
	public PROJ2_USER authenticate(PROJ2_USER user) {
		System.out.println("h9");

		Session session=sessionFactory.openSession();
		System.out.println("h10");

		Query query=session.createQuery("from PROJ2_USER where username='"+user.getUsername()+"'  and password='"+user.getPassword()+"'");
		//select * from User where username='smith' and password='123'
		System.out.println("h11");

//		query.setString(0, user.getUsername());
//		query.setString(1, user.getPassword());
	    System.out.println("name"+user.getUsername());

		PROJ2_USER validUser=(PROJ2_USER)query.uniqueResult();

		session.close();

		return validUser;
		
	}
	@Override
	public void updateUser(PROJ2_USER user) {
		Session session=sessionFactory.openSession();
		PROJ2_USER existingUser=(PROJ2_USER)session.get(PROJ2_USER.class, user.getId());
		//update online status as true
		existingUser.setOnline(user.isOnline()); 
		
		session.update(existingUser);
		session.flush();
		session.close();
	}
	@Override
	public PROJ2_USER registerUser(PROJ2_USER user) {

		Session session=sessionFactory.openSession();
		session.save(user);
		session.flush();
		session.close();
		
		return user;
	}

}