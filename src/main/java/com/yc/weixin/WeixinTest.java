package com.yc.weixin;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;

import com.yc.controller.UserController;
import com.yc.po.AccessToken;
import com.yc.wechat.WeiXinUtil2;
import com.yc.wechat.sign;

public class WeixinTest {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		AccessToken request = WeiXinUtil2.getAccessToken();
		System.out.println("token="+request.getToken());
		AccessToken token = WeiXinUtil2.getJsApiTicket(request.getToken());
		System.out.println("票据："+token.getJsapiTicket());
		
		
        // 注意 URL 一定要动态获取，不能 hardcode
        /*Map<String, String> ret = sign.sign(token.getJsapiTicket(), "http%3A%2F%2Flocalhost%3A8080%2Fxhl-h5%2Fmain");
        for (Map.Entry entry : ret.entrySet()) {
        	System.out.println(entry.getKey() + "={ " + entry.getValue()+"}");
        }*/
		
//		UserController user = new UserController();
//		HttpSession session = null;
//		
//		user.getOpenId("011MtxX40ZlDiC12HAX40X1HX40MtxXm", session);
	}

}
