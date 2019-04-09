package com.tong.musicplayer.service;

import java.util.List;

import com.tong.musicplayer.entity.Album;
import com.tong.musicplayer.entity.Label;
import com.tong.musicplayer.entity.Music;
import com.tong.musicplayer.entity.Singer;

public interface MusicService {
	
	/*********************歌曲***************************/
	//获取所有歌曲
	List<Music> getAll();
	
	//通过音乐id找到音乐
	List<Music> getByMusicId(Integer id);
	
	//通过音乐名查询歌曲集
	List<Music> getByMusicName(String Mname);
	
	//通过歌手名字查询歌曲集
	List<Music> getBySingerName(String Sname);
	
	//通过专辑id获得旗下的所有音乐
	Album selectMusicsById(Integer id);
	
	//从singer的id到音乐集
	List<Music> selectBySingerId(Integer id);
	
	//通过歌手id找到旗下所有专辑下的所有音乐
	List<Album> selectMusicsBySingerId(Integer id);
	
	/*********************专辑***************************/
	//通过专辑id得到专辑
	Album selectAlbumByAlbumId(Integer id);
	
	//通过专辑名字查询专辑集
	List<Album> getAlbumsByAlbumName(String Aname);
	
	//通过歌手id获得旗下的所有专辑
	Singer selectAlbumsById(Integer id);
	
	//通过音乐id找到唯一专辑
	Music selectAlbumById(Integer id);
	
	
	/*********************歌手***************************/
	//通过专辑id找到唯一歌手
	Album selectSingerById(Integer id);
	
	//通过歌手名字找到歌手
	List<Singer> selectSingerBySname(String name);
	
	//获取所有歌手
	List<Singer> selectAll();

	//获取歌手列表
	 List<Singer> selectByLangAndGenderAndBand(String lang, Boolean gender, Boolean isBand);
	 
	 //获取所有标签
	 List<Label> selectAllLabers();
	 
	 

}
