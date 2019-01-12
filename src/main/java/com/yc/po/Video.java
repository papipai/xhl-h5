package com.yc.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 视频实体类
 * @author Administrator
 *
 */
public class Video implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String src;
	private String main_pic;
	private int click_num;
	private String title;
	private Date create_time;
	private int key_point;
	private String video_derc;
	private String delete_yn;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	
	public String getMain_pic() {
		return main_pic;
	}
	public void setMain_pic(String main_pic) {
		this.main_pic = main_pic;
	}
	public int getClick_num() {
		return click_num;
	}
	public void setClick_num(int click_num) {
		this.click_num = click_num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public int getKey_point() {
		return key_point;
	}
	public void setKey_point(int key_point) {
		this.key_point = key_point;
	}
	public String getVideo_derc() {
		return video_derc;
	}
	public void setVideo_derc(String video_derc) {
		this.video_derc = video_derc;
	}
	public String getDelete_yn() {
		return delete_yn;
	}
	public void setDelete_yn(String delete_yn) {
		this.delete_yn = delete_yn;
	}
	
	
}
