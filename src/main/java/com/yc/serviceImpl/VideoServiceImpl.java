package com.yc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.mybatis.mapper.VideoDao;
import com.yc.po.Video;
import com.yc.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService{
	@Autowired
	private VideoDao videoDao;
	
	@Override
	public List<Video> getAllVideo() {
		
		return videoDao.getAllVideo();
	}
	
	@Override
	public Video getVideoById(int videoId) {
		
		return videoDao.getVideoById(videoId);
	}

	@Override
	public Video getNewVideo() {
		
		return videoDao.getNewVideo();
	}

}
