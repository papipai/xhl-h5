package com.yc.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.yc.po.Video;
import com.yc.po.WeiXinUser;
import com.yc.serviceImpl.VideoServiceImpl;
import com.yc.serviceImpl.WeiXinUserServiceImpl;
import com.yc.wechat.WeiXinUtil2;


@Controller  
public class UserController {  
    
    @Autowired  
    private WeiXinUserServiceImpl weiXinuserServiceImpl; 
    
    @Autowired
	private VideoServiceImpl videoServiceImpl;
    
    private static final String CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";//引导用户授权链接
    
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    
    private static String OPENID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	
    /*
     * 视频主页
     */
    @RequestMapping(value="/main",method= {RequestMethod.GET})
    public String showAllUsers(@RequestParam("videoId")String videoId,HttpSession session){ 
    	Video video = new Video();
    	if (videoId != null && videoId != "") {
    		video = videoServiceImpl.getVideoById(Integer.parseInt(videoId));
    		session.setAttribute("video", video);
		}else {
			video = videoServiceImpl.getNewVideo();
			session.setAttribute("video", video);
		}
        return "main";  
    } 
    @RequestMapping(value="/poster.html",method= {RequestMethod.GET})
    public String poster(){ 
    	
        return "poster";  
    } 
    /**
     * 获取openID
     * @param request
     * @return
     * @throws IOException 
     * @throws ClientProtocolException 
     */
//    @RequestMapping(value="/main",method= {RequestMethod.GET})
//    public String getOpenId(HttpServletResponse response,String code) throws ClientProtocolException, IOException {
//    	String url = ACCESS_TOKEN_URL.replace("APPID", WeiXinUtil2.APPID).replace("APPSECRET", WeiXinUtil2.APPSECRET).replace("CODE", "001I6CSk1OAHzI0COsSk10jkSk1I6CSY");
//    	net.sf.json.JSONObject jsonObject = WeiXinUtil2.doGetStr(url);
//    	WeiXinUser user = new WeiXinUser();
//    	String openId = "";
//    	if(jsonObject!=null){ //如果返回不为空，将返回结果封装进AccessToken实体类
//    		openId = jsonObject.getString("openid");//取出openid
//            user.setOpen_id(openId);
//            weiXinuserServiceImpl.addWeiXinUser(user);
//            System.out.println(jsonObject.getString("openid"));
//        }
//    	return "main";
//    }
    
    /**
     * 获取请求用户信息的access_token
     *
     * @param code
     * @return
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    @RequestMapping(value="/getOpenId",method= {RequestMethod.GET})
    public void getOpenId(String code,HttpSession session) throws ClientProtocolException, IOException {
    	if (code != null && code != "null" && code != "") {
    		String url = OPENID_URL.replace("APPID", WeiXinUtil2.APPID).replace("SECRET", WeiXinUtil2.APPSECRET).replace("CODE", code);
            WeiXinUser user = new WeiXinUser();
            WeiXinUser user2 = new WeiXinUser();
            String openId = null;
            net.sf.json.JSONObject jsonObject = WeiXinUtil2.doGetStr(url);
            if (jsonObject != null) {
            	openId = (String) jsonObject.get("openid");
            	user.setOpen_id(openId);
            	session.setAttribute("openId", openId);//存入session方便后面使用
            	//判断该openID是否已存在
            	user = weiXinuserServiceImpl.getWeiXinUser(user);
            	
            	if (user == null) {
            		user2.setOpen_id(openId);
            		user2.setCreate_time(new Date());
            		weiXinuserServiceImpl.addWeiXinUser(user2);
    			}
    		}
		}else {
			System.out.println("code为空！！");
		}
    }
    
    /*  获取当前访问的URL
     * 
     */
    public String getCurrentUrl(HttpServletRequest request) {
    	HttpServletRequest httpRequest=(HttpServletRequest)request;
    	String strBackUrl = "http://" + request.getServerName() + ":"
    			+ request.getServerPort()
    			+ httpRequest.getContextPath()
    			+ httpRequest.getServletPath()
    			+ "?" + (httpRequest.getQueryString());
    			System.out.println("strBackUrl: " + strBackUrl);
    	return strBackUrl;
    }
     
}  