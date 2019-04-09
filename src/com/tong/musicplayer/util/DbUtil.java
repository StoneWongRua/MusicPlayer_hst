package com.tong.musicplayer.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tong
 *
 * @Description
 */
public class DbUtil {
	
	public Statement stmt = null;
	public ResultSet rs = null;
	private static final String URL = "jdbc:mysql://localhost:3306/tong_music?useSSL=false";
	private static final String NAME = "root";
	private static final String PSW = "root";
	private static Connection conn = null;
	
	/**
	 * @Description 加载数据库驱动
	 */
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @Description 连接数据库
	 */
	public static Connection getConnection() {
		try {
			conn = (Connection) DriverManager.getConnection(URL, NAME, PSW);
			System.out.println("数据库连接成功XD");
			return conn;
		}
		catch (SQLException e) {
			System.out.println("数据库连接失败");
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * @Description 关闭数据库连接
	 */
	public void close() {
		try {
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	/**
	 * @Description 给预编译执行语句块的占位符赋值
	 */
	public void setParams(PreparedStatement psmt, Object...params) {
		if(params != null && params.length > 0) {
			for(int i = 0, len = params.length; i < len; i++) {
				try {
					if("".equals((String)params[i])) {
						params[i] = "参数不详";
					}
					psmt.setObject(i+1, params[i]);
				}catch(SQLException e) {
					System.out.println("第" + (i + 1) + "个参数注值失败:(");
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * @Description 给预编译执行语句块的占位符赋值
	 */
	public void setParams(PreparedStatement psmt, List<List<Object>> params) {
		if(params != null && params.size() > 0) {
			for(int i = 0, len = params.size(); i < len; i++) {
				try {					
					psmt.setObject(i+1, params.get(i));
				}catch(SQLException e) {
					System.out.println("第" + (i + 1) + "个参数注值失败:(");
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * @Description 执行查询语句
	 */
	public List<Map<String, Object>> finds(String sql, Object...params){
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>(); 
		
		try {
			con = this.getConnection();
			psmt = con.prepareStatement(sql);
			this.setParams(psmt, params);
			rs = psmt.executeQuery();
			ResultSetMetaData rsm = rs.getMetaData();
			
			int colCount = rsm.getColumnCount();
			String[] colNames = new String[colCount];
			for(int i = 1; i <= colCount; i++) {
				colNames[i - 1] = rsm.getColumnName(i).toLowerCase();
			}
			Map<String, Object> map = null;
			while(rs.next()) {
				map = new HashMap<String, Object>();
				for(String colName:colNames) {
					map.put(colName, rs.getObject(colName));
				}
				list.add(map);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.close();
		}
		return list;
		
	}
	
	/**
	 * @Description 执行查询语句
	 */
	public List<Map<String,String>> findStr(String sql,String... params) {
		Connection con = null; 
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();

		try {
			con = this.getConnection(); //获取连接
			pstmt = con.prepareStatement(sql); //预编译执行语句

			this.setParams(pstmt, params);  //赋值
			rs=pstmt.executeQuery(); //执行语句
			ResultSetMetaData rsm = rs.getMetaData(); 

			int colCount = rsm.getColumnCount();
			String[] colNames = new String[colCount];

			for (int i = 1; i <=colCount; i++) {
				colNames[i-1] = rsm.getColumnName(i).toLowerCase();
			}
			Map<String, String> map=null; //每一行的数据存到一个LISt中
			while (rs.next()) {
				map=new HashMap<String, String>();
				for(String colName:colNames) { //取每一列的值
					map.put(colName,rs.getString(colName));
				}
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.close();
		}
		return list;
	}
	
	/**
	 * @Description 执行查询语句
	 */
	public List<String> findOneStr(String sql,String... params) {
		Connection con = null; 
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();

		try {
			con = this.getConnection(); //获取连接
			pstmt = con.prepareStatement(sql); //预编译执行语句

			this.setParams(pstmt, params);  //赋值
			rs=pstmt.executeQuery(); //执行语句
			ResultSetMetaData rsm = rs.getMetaData(); 

			int colCount = rsm.getColumnCount();
			String[] colNames = new String[colCount];

			for (int i = 1; i <=colCount; i++) {
				colNames[i-1] = rsm.getColumnName(i).toLowerCase();
			}
			//Map<String, String> map=null; //每一行的数据存到一个LISt中
			while (rs.next()) {
				//	map=new HashMap<String, String>();
				for(String colName:colNames) { //取每一列的值
					list.add(rs.getString(colName));				
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.close();
		}
		return list;
	}
	
	/**
	 * @Description 执行查询语句
	 */
	public List<Map<String,Object>> finds(String sql,List<Object> params) {
		Connection con = null; 
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

		try {
			con = this.getConnection(); //获取连接
			pstmt = con.prepareStatement(sql); //预编译执行语句

			this.setParams(pstmt, params);  //赋值
			rs=pstmt.executeQuery(); //执行语句
			ResultSetMetaData rsm = rs.getMetaData(); 

			int colCount = rsm.getColumnCount();
			String[] colNames = new String[colCount];

			for (int i = 1; i <=colCount; i++) {
				colNames[i-1] = rsm.getColumnName(i).toLowerCase();
			}
			Map<String, Object> map=null; //每一行的数据存到一个LISt中
			while (rs.next()) {
				map=new HashMap<String, Object>();
				for(String colName:colNames) { //取每一列的值
					map.put(colName,rs.getObject(colName));
					//System.out.println(rs.getObject(colName));
				}
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.close();
		}
		return list;
	}
	
	/**
	 * @Description 执行数据库更新
	 */
	public int update(String sql,List<Object> params) {
			Connection con = null;
			PreparedStatement psmt = null;

			try {
				con = this.getConnection();
				psmt = con.prepareStatement(sql);
				this.setParams(psmt, params);
				return psmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				this.close();
			}
			return 0;
	}
	
	
	public int update(String sql,Object... params) {
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = this.getConnection();
			psmt = con.prepareStatement(sql);
			this.setParams(psmt, params);
			//	System.out.println(psmt.equals(obj));
			return psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.close();
		}
		return 0;
	}
	
	/**
	 * @Description 获取所有记录
	 */
	public int  getTotal(String sql,Object...params) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con= this.getConnection();
			pstmt = con.prepareStatement(sql);
			this.setParams(pstmt, params);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.close();
		}
		return 0;
	}
	
	/**
	 * @Description 获取所有记录
	 */
	public int getTotal(String sql,List<Object> params){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con= this.getConnection();
			pstmt = con.prepareStatement(sql);
			this.setParams(pstmt, params);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.close();
		}
		return 0;
	}
	
/*	public static void main(String args[]){
		conn = getConnection();
		
	}*/

	


}
