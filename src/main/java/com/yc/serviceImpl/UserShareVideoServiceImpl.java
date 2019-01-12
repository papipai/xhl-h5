package com.yc.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.mybatis.mapper.UserShareVideoDao;
import com.yc.po.UserShareVideo;
import com.yc.service.UserShareVideoService;

@Service
public class UserShareVideoServiceImpl implements UserShareVideoService{
	@Autowired
    private UserShareVideoDao userShareVideoDao;

	@Override
	public String getUserShareVideo(UserShareVideo userShare) {
		// TODO Auto-generated method stub
		return userShareVideoDao.getUserShareVideo(userShare);
	}

	@Override
	public int addUserShareVideo(UserShareVideo userShare) {
		// TODO Auto-generated method stub
		return userShareVideoDao.addUserShareVideo(userShare);
	}  
	
}