package com.tong.musicplayer.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.tong.musicplayer.service.LocalMusicService;
import com.tong.musicplayer.util.DbUtil;

public class LocalMusicImpl implements LocalMusicService {

	@Override
	public int insertTo(String musicName, String singer, String collection, String musicTime, String size,
			String filepath) {
		// TODO Auto-generated method stub
		String sql="insert into music_new(music_name, singer, collection, music_time, music_size,filepath) values(?,?,?,?,?,?)";
		DbUtil db = new DbUtil();
/*		try {
			PreparedStatement ps = db.getConnection().prepareStatement("insert into music_new(music_name, singer, collection, music_time, music_size,filepath) values(?,?,?,?,?,?)");
			ps.setString(1, musicName);
			ps.setString(2, singer);
			ps.setString(3, collection);
			ps.setString(4, musicTime);
			ps.setString(5, size);
			ps.setString(6, filepath);
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		int result = db.update(sql, musicName, singer, collection, musicTime, size,filepath);
		return result;
	}

	@Override
	public List<Map<String, String>> findAll(String[] filepath) {
		// TODO Auto-generated method stub
		String sql= "select music_name, singer, collection, music_time, music_size from music_new where filepath like '" + filepath[0] + "%' ";
		for(int i = 1, len = filepath.length; i < len; i ++) {
			sql += "or filepath like '" + filepath[i] + "%'";
		}
		sql += "order by music_name";
		DbUtil db = new DbUtil();
		List<Map<String, String>> list = db.findStr(sql);
		return list;
	}

	@Override
	public int insertLoPath(String filepath) {
		// TODO Auto-generated method stub
		String sql="insert into fileDirectory values(?)";
		DbUtil db = new DbUtil();
		int result = db.update(sql, filepath);
		return result;
	}

	@Override
	public List<Map<String, String>> findPath() {
		// TODO Auto-generated method stub
		String sql="select filepath from filedirectory";
		DbUtil db = new DbUtil();
		List<Map<String, String>> list = db.findStr(sql);
		return list;
	}

	/**
	 * 获取播放文件路径
	 * @param name 歌曲名
	 * @param time 歌曲时长，转换为秒传入，例：356
	 * @param filepath 固定传入 DataDic.localPath
	 * @return
	 */
	@Override
	public List<String> findOne(String name,String time,String[] filepath) {
		String sql="select id,filepath from music_new where music_name=? and music_time=? and filepath like '"+filepath[0]+"%'";
		for (int i = 1,len=filepath.length; i < len; i++) {
			sql+=" or filepath like '"+filepath[i]+"%' ";
		}
		sql+=";";
		System.out.println(sql);
		DbUtil dbH = new DbUtil();
		List<String> list = dbH.findOneStr(sql,name,time);
		return list;
	}
	
	@Override
	public List<Map<String, String>> searchAll(String name, String[] filepath) {
		// TODO Auto-generated method stub
		DbUtil db = new DbUtil();
		List<Map<String, String>> list = null;
		if(!"".equals(name)) {
			String sql = "select music_name, singer, collection, music_time, music_size from music_new where music_name like '" + name + "%' or music_name like '%" + 
							name + "' or music_name like '%" + name + "%' and filepath like '"+filepath[0]+"%'";
			for (int i = 1,len=filepath.length; i < len; i++) {
				sql+=" or filepath like '"+filepath[i]+"%' ";
			}
			sql+=";";
			System.out.println(sql);
			list = db.findStr(sql);
		}
		
		return list;
	}

	@Override
	public int delete(String fstr) {
		// TODO Auto-generated method stub
		DbUtil db = new DbUtil();
		String sql_1 = "delete from music_new where filepath = ?";
		String sql_2 = "select id from music_new where filepath = ?";
		List<Map<String, String>> list = db.findStr(sql_2, fstr);
		if(list != null && list.size()>0) {
			sql_2 = "delete from recentlist where id = ?";
			db.update(sql_2, list.get(0).get("id"));
		}
		int result = db.update(sql_1, fstr);
		return result;
	}

	@Override
	public List<String> findSeg(String[] filepath) {
		// TODO Auto-generated method stub
		String sql = "select filepath from music_new where filepath like '" + filepath[0] + "%'";
		for (int i = 1,len=filepath.length; i < len; i++) {
			sql+=" or filepath like '"+filepath[i]+"%' ";
		}
		DbUtil db = new DbUtil();
		List<String> list = db.findOneStr(sql);
		return list;
		
	}

}
