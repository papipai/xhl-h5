package com.yc.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.mybatis.mapper.WeiXinUserDao;
import com.yc.po.WeiXinUser;
import com.yc.service.WeiXinUserService;

@Service
public class WeiXinUserServiceImpl implements WeiXinUserService{
	@Autowired
    private WeiXinUserDao weiXinuserDao;  

	//添加用户
	public int addWeiXinUser(WeiXinUser user) {
		// TODO Auto-generated method stub
		return weiXinuserDao.addWeiXinUser(user);
	}
	//更新用户
	public int updateWeiXinUser(WeiXinUser user) {
		// TODO Auto-generated method stub
		return weiXinuserDao.updateWeiXinUser(user);
	}
	//删除指定id用户
	public int deleteWeiXinUserById(int id) {
		// TODO Auto-generated method stub
		return weiXinuserDao.deleteWeiXinUserById(id);
	}
	@Override
	public WeiXinUser getWeiXinUser(WeiXinUser user) {
		
		return weiXinuserDao.getWeiXinUser(user);
	}
}