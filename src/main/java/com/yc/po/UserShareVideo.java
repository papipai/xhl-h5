package com.yc.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户分享视频
 * @author Administrator
 *
 */
public class UserShareVideo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String open_id;
	private int video_id;
	private Date share_time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOpen_id() {
		return open_id;
	}
	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}
	public int getVideo_id() {
		return video_id;
	}
	public void setVideo_id(int video_id) {
		this.video_id = video_id;
	}
	public Date getShare_time() {
		return share_time;
	}
	public void setShare_time(Date share_time) {
		this.share_time = share_time;
	}
	
	
	
}
