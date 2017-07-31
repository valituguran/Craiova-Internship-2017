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

	public static boolean validate(String name, String pass) {
		boolean status = false;
		Connection conn = connect();
		PreparedStatement pst = null;
		ResultSet rs = null;
		int type;
		int tip = 0;

		try {
			pst = conn.prepareStatement("select * from users where username=? and password=?");
			pst.setString(1, name);
			pst.setString(2, pass);
			rs = pst.executeQuery();
			status = rs.next();

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
		return status;
	}

	public static int getUserType(String name, String pass) {
		PreparedStatement ps ;
		Connection conn = RegisterDao.connect();
        ResultSet user_type ;
        int type  = 2 ;
		try {
			ps = conn.prepareStatement(" select * from users where username=? and password=? ");
			ps.setString(1,"name");
			ps.setString(2,"pass");
			user_type = ps.executeQuery();
			if(user_type.next()) {


				type = user_type.getInt("type");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
         return type;
	}
}