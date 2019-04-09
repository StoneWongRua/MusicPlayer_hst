package com.tong.musicplayer.impl;

import java.util.List;
import java.util.Map;

import com.tong.musicplayer.service.RecentListService;
import com.tong.musicplayer.util.DbUtil;

public class RecentListImpl implements RecentListService {

	@Override
	public List<Map<String, String>> findAll() {
		// TODO Auto-generated method stub
		DbUtil db = new DbUtil();
		String sql = "select music_name, singer, frequency from recentlist r join music_new l on r.id = l.id";
		List<Map<String, String>> list = db.findStr(sql);
		return list;
	}

	@Override
	public List<Map<String, String>> findList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, String>> findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, String>> findId(String musicName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, String>> findDetail(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertTo(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
