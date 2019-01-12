package com.yc.service;

import com.yc.po.UserShareVideo;

public interface UserShareVideoService {  
	/**
	 * 查询
	 * @return
	 */
	public String getUserShareVideo(UserShareVideo userShare);
	/**
	 * 新增
	 * @param user
	 * @return
	 */
	public int addUserShareVideo(UserShareVideo userShare);
}  
