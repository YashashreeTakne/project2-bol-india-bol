package com.yashashree.dao;

import com.yashashree.model.PROJ2_USER;

public interface UserDao {
	PROJ2_USER authenticate(PROJ2_USER user);
    void updateUser(PROJ2_USER user);
	PROJ2_USER registerUser(PROJ2_USER user);
}