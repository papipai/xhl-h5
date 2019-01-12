package com.yc.service;

import com.yc.po.WeiXinUser;

public interface WeiXinUserService {  
	/**
	 * 查找用户
	 * @param user
	 * @return
	 */
	public WeiXinUser getWeiXinUser(WeiXinUser user);
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	public int addWeiXinUser(WeiXinUser user);
	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	public int updateWeiXinUser(WeiXinUser user);
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public int deleteWeiXinUserById(int id);
}  
