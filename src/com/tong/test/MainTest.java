package com.tong.test;

import com.tong.musicplayer.impl.LocalMusicImpl;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalMusicImpl lms = new LocalMusicImpl();
		String[] filepath = new String[3];
		filepath[0] = "hst";
		filepath[1] = "a";
		filepath[2] = "C:";
		System.out.println(lms.findAll(filepath));
		lms.insertLoPath(filepath[2]);
		System.out.println(lms.findPath());
	}

}
