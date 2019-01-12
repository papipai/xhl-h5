package com.yc.mybatis.mapper;

import org.springframework.stereotype.Repository;

import com.yc.po.AccessToken;

@Repository
public interface AccessTokenDao {
	public	AccessToken selectToken();
	public	int updateToken(AccessToken accessToken);
	public	int addToken(AccessToken accessToken);
}
