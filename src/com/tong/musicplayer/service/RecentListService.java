package com.tong.musicplayer.service;

import java.util.List;
import java.util.Map;

public interface RecentListService {
	
	public List<Map<String, String>> findAll();
	
	public List<Map<String, String>>  findList();
	
	public List<Map<String, String>>  findOne(String id);
	
	public List<Map<String, String>>  findId(String musicName);
	
	public List<Map<String, String>>  findDetail(String id);
	
	public int insertTo(String id);
	

}
