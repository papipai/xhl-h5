package com.yc.mybatis.mapper;


import org.springframework.stereotype.Repository;

import com.yc.po.WeiXinUser;

@Repository
public interface WeiXinUserDao {
	//查找用户
	public WeiXinUser getWeiXinUser(WeiXinUser user);
	//添加用户
	public int addWeiXinUser(WeiXinUser user);
    //更新
	public int updateWeiXinUser(WeiXinUser user);
    //删除
	public int deleteWeiXinUserById(int id);
}
