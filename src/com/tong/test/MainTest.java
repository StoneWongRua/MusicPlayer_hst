package com.tong.test;

import com.tong.musicplayer.impl.LocalMusicImpl;
import com.tong.musicplayer.impl.RecentListImpl;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalMusicImpl lms = new LocalMusicImpl();
		String[] filepath = new String[3];
		filepath[0] = "hst";
		filepath[1] = "a";
		filepath[2] = "hhhh";
		System.out.println(lms.findAll(filepath));
		//lms.insertLoPath(filepath[2]);
		System.out.println(lms.findPath());
		System.out.println(lms.findOne("hst", "hst", filepath));
		System.out.println(lms.searchAll("hst", filepath));
		lms.delete("hhhh");
		System.out.println(lms.findAll(filepath));
		System.out.println(lms.findSeg(filepath));
		
		RecentListImpl rls = new RecentListImpl();
		System.out.println(rls.findAll());
	}

}
