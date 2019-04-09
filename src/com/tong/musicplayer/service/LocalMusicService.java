package com.tong.musicplayer.service;

import java.util.List;
import java.util.Map;

public interface LocalMusicService {
	
	int insertTo(String musicName, String singer, String collection, String musicTime, String size, String filepath);
	
	List<Map<String, String>> findAll(String[] filepath);
	
	int insertLoPath(String filepath);
	
	List<Map<String, String>> findPath();
	
	//获取播放文件路径
	List<String> findOne(String name,String time,String[] filepath);
	
	//搜索本地音乐
	List<Map<String, String>> searchAll(String name,String[] filepath);
	
	int delete(String fstr);
	
	//提取部分信息
	List<String> findSeg(String[] filepath);
	
	
	
}
