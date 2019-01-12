package com.yc.service;

import com.yc.po.AccessToken;

public interface AccessTokenService {
	public AccessToken selectToken();
	public int updateToken(AccessToken accessToken);
	public int addToken(AccessToken accessToken);
}
