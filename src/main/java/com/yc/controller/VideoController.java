package com.yc.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.yc.po.AccessToken;
import com.yc.po.UserShareVideo;
import com.yc.po.Video;
import com.yc.serviceImpl.AccessTokenServiceImpl;
import com.yc.serviceImpl.UserShareVideoServiceImpl;
import com.yc.serviceImpl.VideoServiceImpl;
import com.yc.serviceImpl.WeiXinUserServiceImpl;
import com.yc.wechat.WeiXinUtil2;
import com.yc.wechat.sign;


@Controller
public class VideoController {
	
	@Autowired
	private VideoServiceImpl videoServiceImpl;
	
	@Autowired
	private AccessTokenServiceImpl accessTokenServiceImpl;
	
	@Autowired  
    private WeiXinUserServiceImpl weiXinuserServiceImpl; 
	
	@Autowired
	private UserShareVideoServiceImpl userShareVideoServiceImpl;
	
	

	@RequestMapping(value="/getVideo",method= {RequestMethod.GET})
	public void getAllVideo(HttpServletResponse response) throws IOException {
		
		List<Video> videos = videoServiceImpl.getAllVideo();
		
		Gson gson = new Gson();
		String recJson = gson.toJson(videos);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(recJson);
	}
	
	
    @RequestMapping(value="/initWXJSInterface",method= {RequestMethod.GET})
    public void initWXJSInterface(String url,HttpServletResponse response) throws ClientProtocolException, IOException{
    	url = URLDecoder.decode(url,"UTF-8");//解码
    	System.out.println("URLDecoder.decode(url,\"UTF-8\")="+URLDecoder.decode(url,"UTF-8"));
    	//AccessToken accessToken = WeiXinUtil2.getAccessToken();
    	AccessToken accessToken = new AccessToken();
    	//从数据库中获取缓存的access_token
    	accessToken = accessTokenServiceImpl.selectToken();
    	if (accessToken == null) {
    		accessToken = WeiXinUtil2.getAccessToken();
    		accessTokenServiceImpl.addToken(accessToken);
		}
    	String jsapi_ticket = "";
    	
    	Map<String, String> ret = new HashMap<>();
    	if (accessToken != null) {
    		jsapi_ticket  = WeiXinUtil2.getJsApiTicket(accessToken.getToken()).getJsapiTicket();
    		ret = sign.sign(jsapi_ticket, url);
		}
    	
		Gson gson = new Gson();
		String recJson = gson.toJson(ret);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(recJson);
	}
    
    /**
     * 判断是否分享
     * @param videoId
     * @param session
     * @param response
     * @throws IOException
     */
	@RequestMapping(value="/isShareVideo",method= {RequestMethod.GET})
	public void isShareVideo(int videoId,HttpSession session,HttpServletResponse response) throws IOException {
		String openId = (String) session.getAttribute("openId");
		UserShareVideo userShare = new UserShareVideo();
		userShare.setOpen_id(openId);
		userShare.setVideo_id(videoId);
		String result = userShareVideoServiceImpl.getUserShareVideo(userShare);

		String code = null;
		if (result != null) {
			code = "yes";//已分享
		}else {
			code = "no";//未分享
		}
		
		response.getWriter().write(code);
	}
	
	@RequestMapping(value="/addVideoShare",method= {RequestMethod.GET})
	public void addVideoShare(int videoId,HttpSession session,HttpServletResponse response) throws IOException {
		String openId = (String) session.getAttribute("openId");
		UserShareVideo userShare = new UserShareVideo();
		UserShareVideo userShare2 = new UserShareVideo();
		userShare.setOpen_id(openId);
		userShare.setVideo_id(videoId);
		
		String isShare = userShareVideoServiceImpl.getUserShareVideo(userShare);
		
		int result = 0;
		String code = null;
		
		if (isShare == null) {
			userShare2.setOpen_id(openId);
			userShare2.setVideo_id(videoId);
			userShare2.setShare_time(new Date());
			result = userShareVideoServiceImpl.addUserShareVideo(userShare2);
			
			if (result > 0) {
				code = "yes";//已分享
			}else {
				code = "no";//为分享
			}
		}else {
			code = "yes";//已分享
		}
		
		response.getWriter().write(code);
	}
}
