package com.yc.weixin;

import java.io.IOException;
import java.util.Date;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yc.controller.VideoController;
import com.yc.po.AccessToken;
import com.yc.serviceImpl.AccessTokenServiceImpl;
import com.yc.wechat.WeiXinUtil2;

@Component
public class AccessTask {
	@Autowired
	private AccessTokenServiceImpl accessTokenServiceImpl;
	@Scheduled(cron = "0 0 * * *  ?")//每小时执行一次0/5 * * * * ?  0 0 * * *  ?
	public void task() throws ClientProtocolException, IOException {
		System.out.println("定时器开始运行，每小时运行一次......");
		
		WeiXinUtil2 weiXinUtil2 = new WeiXinUtil2();
    	AccessToken accessToken = weiXinUtil2.getAccessToken();
    	System.out.println("accessToken="+accessToken.getToken());
		String token = accessToken.getToken();
		String jsapiTicket = weiXinUtil2.getJsApiTicket(token).getJsapiTicket();
		System.out.println("jsapiTicket="+jsapiTicket);
		
		accessToken.setJsapiTicket(jsapiTicket);
		accessToken.setGetTime(new Date());
		//accessTokenServiceImpl.addToken(accessToken);
		accessTokenServiceImpl.updateToken(accessToken);
	}
}
