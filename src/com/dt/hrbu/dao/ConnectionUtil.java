package com.dt.hrbu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
		private static Connection conn;
		public static Connection getConnection() {
			if(conn==null) {
				try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/stu_manager?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC","root","123");
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
			return conn;
			
		}
		public static void main(String[] args) {
			System.out.println(ConnectionUtil.getConnection());
		}
}
