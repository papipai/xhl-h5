package com.yc.mybatis.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yc.po.Video;

@Repository
public interface VideoDao {
	public List<Video> getAllVideo();
	public Video getVideoById(int videoId);
	public Video getNewVideo();
}
