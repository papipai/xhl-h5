package com.yc.service;

import java.util.List;

import com.yc.po.Video;

public interface VideoService {
	
	public List<Video> getAllVideo();
	
	public Video getVideoById(int videoId);
	
	public Video getNewVideo();
}
