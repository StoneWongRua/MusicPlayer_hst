package com.tong.musicplayer.entity;

public class MusicNew {
	private String musicName;
	private String singer;
	private String musicTime;
	private String filepath;
	
	public MusicNew(String musicName, String singer, String musicTime, String filepath) {
		super();
		this.musicName = musicName;
		this.singer = singer;
		this.musicTime = musicTime;
		this.filepath = filepath;
	}

	public String getMusicName() {
		return musicName;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getMusicTime() {
		return musicTime;
	}

	public void setMusicTime(String musicTime) {
		this.musicTime = musicTime;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	@Override
	public String toString() {
		return "MusicNew [musicName=" + musicName + ", singer=" + singer + ", musicTime=" + musicTime + ", filepath="
				+ filepath + "]";
	}
	
	

}
