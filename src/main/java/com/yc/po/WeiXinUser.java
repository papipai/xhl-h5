package com.yc.po;

import java.io.Serializable;
import java.util.Date;

public class WeiXinUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String open_id;
	private String name;
	private String sex;
	private String is_attention;//是否关注
	private String is_share;//是否分享
	private String share_object;//分享对象
	private Date create_time;
	private String code;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIs_attention() {
		return is_attention;
	}
	public void setIs_attention(String is_attention) {
		this.is_attention = is_attention;
	}
	public String getIs_share() {
		return is_share;
	}
	public void setIs_share(String is_share) {
		this.is_share = is_share;
	}
	public String getShare_object() {
		return share_object;
	}
	public void setShare_object(String share_object) {
		this.share_object = share_object;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
		


}
