package com.tong.musicplayer.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private static final long serialVersionUID = 4799602879543296825L;

    private Integer id;

    private String username;

    private String password;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    @Override
    public String toString() {
        List<String> list = new ArrayList<>();

        list.add(id == null ? null : id.toString());
        list.add(username);
        list.add(password);

        return list.toString();
    }
    
    public User() {}

    public User(
      Integer id,
      String username,
      String password
    ) {
        this.id = id;
        this.username = username == null ? null : username.trim();
        this.password = password == null ? null : password.trim();
    }
}
