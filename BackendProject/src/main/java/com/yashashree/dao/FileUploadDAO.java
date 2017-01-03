package com.yashashree.dao;

import com.yashashree.model.ProfilePhoto;

public interface FileUploadDAO {
	void save(ProfilePhoto uploadFile);
	ProfilePhoto getFile(String username);
}
