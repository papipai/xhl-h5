package com.yc.mybatis.mapper;


import org.springframework.stereotype.Repository;

import com.yc.po.UserShareVideo;

@Repository
public interface UserShareVideoDao {
	//查找
	public String getUserShareVideo(UserShareVideo userShare);
	//添加
	public int addUserShareVideo(UserShareVideo userShare);
}
