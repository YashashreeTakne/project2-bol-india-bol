package com.yashashree.controllers;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yashashree.dao.UserDao;
import com.yashashree.model.Error;
import com.yashashree.model.PROJ2_USER;
@RestController
public class UserController {
@Autowired
private UserDao userDao;
//isOnline - set true when the user login
//isOnline -set false when the user logout
@RequestMapping(value="/login",method=RequestMethod.POST)
public ResponseEntity<?> login(@RequestBody PROJ2_USER user,HttpSession session){
	// ? means it can return any type of object [Error, User]
	
	// the method login has to return any Type  
	//if the user is invalid - return Error object with status code
	//if the user is valid  - return User object with status code
	System.out.println("h5");
	PROJ2_USER validUser=userDao.authenticate(user);
	System.out.println("h6");

	if(validUser==null){
		System.out.println("h7");

		Error error=new Error(1,"Username and password doesnt exists...");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED); //401
	}

	else{
		System.out.println("h8");
		session.setAttribute("user", validUser);

		validUser.setOnline(true);
		userDao.updateUser(validUser); // to update online status in db
		return new ResponseEntity<PROJ2_USER>(validUser,HttpStatus.OK);//200
	}
}



//'?'  - Any Type [User,Error] 
//ENDPOINT : http://localhost:8080/proj2backend/register 
@RequestMapping(value="/register",method=RequestMethod.POST)
public ResponseEntity<?> registerUser(@RequestBody PROJ2_USER user){
	//client will send only username, password, email, role 
	try{
//	logger.debug("USERCONTROLLER=>REGISTER " + user);
	user.setStatus(true);
	user.setOnline(false);
	System.out.println("h9");

	PROJ2_USER savedUser=userDao.registerUser(user);
	System.out.println("h10");

//	logger.debug("User Id generated is " + savedUser.getId());
	if(savedUser.getId()==0){
		System.out.println("h111");
		System.out.println("h1111");

		Error error=new Error(2,"Couldnt insert user details ");
		System.out.println("h12");

		return new ResponseEntity<Error>(error , HttpStatus.CONFLICT);
	}
	else
	{
		System.out.println("h13");

		return new ResponseEntity<PROJ2_USER>(savedUser,HttpStatus.OK);
	}
	}catch(Exception e){
		e.printStackTrace();
		System.out.println("h14");

		Error error=new Error(2,"Couldnt insert user details. Cannot have null/duplicate values " + e.getMessage());
		return new ResponseEntity<Error>(error , HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

@RequestMapping(value="/logout",method=RequestMethod.PUT)
public ResponseEntity<?>logout(HttpSession session){
	
	PROJ2_USER user=(PROJ2_USER)session.getAttribute("user");
	if(user!=null)
	{
		user.setOnline(false);
		userDao.updateUser(user);
	}
	session.removeAttribute("user");
	session.invalidate();
	return new ResponseEntity<Void>(HttpStatus.OK);
					
}

}