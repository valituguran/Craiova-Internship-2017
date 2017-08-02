package com.ymens.dao;

import java.sql.*;

public class LoginDao {

	public static Connection connect() {
		Connection conn = null;


		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "bookstore";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "root";

		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			return conn;
		}
	}

	public static int validate(String name, String pass) {
		boolean status = false;
		Connection conn = connect();
		PreparedStatement pst = null;
		ResultSet rs = null;
		int type = 0;


		try {
			pst = conn.prepareStatement("select * from users where username=? and password=?");
			pst.setString(1, name);
			pst.setString(2, pass);
			rs = pst.executeQuery();

			if(rs.next()) {


				type = rs.getInt("type");
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return type;
	}


}