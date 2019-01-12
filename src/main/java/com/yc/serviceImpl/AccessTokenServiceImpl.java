package com.yc.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.mybatis.mapper.AccessTokenDao;
import com.yc.po.AccessToken;
import com.yc.service.AccessTokenService;
@Service
public class AccessTokenServiceImpl implements AccessTokenService {
	@Autowired
	private AccessTokenDao accessTokenDao;
	
	public AccessToken selectToken() {
		return accessTokenDao.selectToken();
	};
	public int updateToken(AccessToken accessToken) {
		return accessTokenDao.updateToken(accessToken);
	};
	public int addToken(AccessToken accessToken) {
		return accessTokenDao.addToken(accessToken);
	};
}
