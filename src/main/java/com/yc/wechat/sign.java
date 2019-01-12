package com.yc.wechat;

import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.http.client.ClientProtocolException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.po.AccessToken;

import java.util.Map;
import java.util.HashMap;
import java.util.Formatter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;  

public class sign {
/*    public static void main(String[] args) throws ClientProtocolException, IOException {
    	AccessToken token = WeiXinUtil2.getJsApiTicket();
        // 注意 URL 一定要动态获取，不能 hardcode
        String url = "http://example.com";
        Map<String, String> ret = sign(token.getJsapiTicket(), url);
        for (Map.Entry entry : ret.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
    };*/
    
//    @RequestMapping("/initWXJSInterface")
//    public @ResponseBody Map<String, String> init(String url) throws ClientProtocolException, IOException{
//    	System.out.println("进入initWXJSInterface");
//    	WeiXinUtil2 weiXinUtil2 = new WeiXinUtil2();
//    	AccessToken token = weiXinUtil2.getJsApiTicket();
//    	//url = URLEncoder.encode(url, "UTF-8");
//		Map<String, String> ret = sign(token.getJsapiTicket(), url);
//		//ret.put("appId", weiXinUtil2.APPID);
//		System.out.println("currurl = "+ url);
////		for (Map.Entry entry : ret.entrySet()) {
////            System.out.println(entry.getKey() + "={ " + entry.getValue()+"}");
////        }
//		//System.out.println("signature =" + ret.get("signature"));
//		return ret;
//	}


    public static Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        //System.out.println(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        
        ret.put("appId", WeiXinUtil2.APPID);
        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
//        for (Map.Entry entry : ret.entrySet()) {
//            System.out.println(entry.getKey() + "={ " + entry.getValue()+"}");
//        }
        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}