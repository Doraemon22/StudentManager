package com.dt.hrbu.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dt.hrbu.dao.ConnectionUtil;
import com.dt.hrbu.service.LoginService;
import com.dt.hrbu.service.Impl.LoginServiceImpl;
import com.dt.hrbu.vo.User;

public class GlobalController extends HttpServlet {
	private Map<String, String> userMap = new HashMap<String, String>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doget doget");

		/*
		 * resp.setContentType("text/html;charset=UTF-8"); PrintWriter
		 * pw=resp.getWriter(); pw.write("<p style='color:pink'>欢迎呀！访问到后台</p>");
		 * pw.flush(); pw.close();
		 */
		resp.sendRedirect("back.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		// System.out.println("run in dopost");
		// 拿到用户请求的url
		System.out.println("uri==" + req.getRequestURI());
		// System.out.println("url=="+req.getRequestURL());
		String uri = req.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/" )+1, uri.indexOf("."));
		if (action.equals("register")) {                                   //============注册
			Connection conn=ConnectionUtil.getConnection();
			PreparedStatement ps=null;
			try {
		 ps=conn.prepareStatement("insert into user(username,password) values(?,?)");
			ps.setString(1, username);
			ps.setString(2, password);
			int i=ps.executeUpdate();
			if(i==1) {
				resp.sendRedirect("register_s.jsp");
			}else {
				resp.sendRedirect("register_f.jsp");
			}
			} catch (SQLException e) {
				resp.sendRedirect("register_f.jsp");
				e.printStackTrace();
			}finally {
				if(ps!=null) {
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
//			userMap.put(username, password);
//			resp.sendRedirect("register_s.jsp");
		} else if (action.equals("login")) {                         //========= 登录，根据用户名获取密码
			/*
			 * String existPassword = userMap.get(username); if (existPassword == null) {
			 * resp.sendRedirect("login_f.jsp"); } else { if
			 * (existPassword.equals(password)) { resp.sendRedirect("login_s.jsp"); } else
			 * {//密码错误 resp.sendRedirect("login_pwrong.jsp"); } }
			 */
	//==========================================================
			
			/*
			 * Connection conn=ConnectionUtil.getConnection(); PreparedStatement ps=null;
			 * //获取session对象（一次会话中解决2个http请求，使其产生联系） HttpSession session=req.getSession();
			 * try { ps=conn.prepareStatement("select * from user where username=?");
			 * ps.setString(1,username); ResultSet rs=ps.executeQuery();
			 * if(rs!=null&&rs.next()) { String p2=rs.getString(2); if(password.equals(p2))
			 * { //密码一致，登录成功 //登录成功后，使用session存用户名，便于在页面上展示用户名
			 * session.setAttribute("username", username); //查询user表下的所有数据 PreparedStatement
			 * ps2= conn.prepareStatement("select  *  from user"); ResultSet
			 * rsList=ps2.executeQuery(); //定义泛型，存储还回的数据 List<User> userList=new
			 * ArrayList<User>(); //遍历 while(rsList.next()) { User user=new User();//实例化User
			 * user.setUsername(rsList.getString(1)); user.setAge(rsList.getInt(3));
			 * userList.add(user); }
			 * 
			 * //将user存到userList中 } //将user存到session中 session.setAttribute("userList",
			 * userList); resp.sendRedirect("login_s.jsp"); }else {
			 * resp.sendRedirect("login_pwrong.jsp"); } }else {
			 * resp.sendRedirect("login_f.jsp"); } } catch (SQLException e) {
			 * e.printStackTrace(); }finally { if(ps!=null) { try { ps.close(); } catch
			 * (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); }
			 * } }
			 */
	//==========================================================
			  User user=new User();
			  user.setUsername(username);
			  user.setPassword(password);
			  //调用service层处理登录逻辑
			  LoginService loginService=new LoginServiceImpl(); 
			  User dbUser=loginService.login(user);
			  if(dbUser==null) {
				resp.sendRedirect("login_f.jsp");
			  }else { 
				  HttpSession session=req.getSession();
				  session.setAttribute("username", dbUser);
				  List<User> userList=loginService.getUserList();     //====
				  session.setAttribute("userList", userList);
			  resp.sendRedirect("login_s.jsp");
			//  resp.sendRedirect("login_pwrong.jsp"); 
			  }
			
		}else {// 其他错误请求
			
		}
	//==============================
		
		/*
		 * if(action.equals("login")) { User user=new User();
		 * user.setUsername(username); user.setPassword(password); //调用service层处理登录逻辑
		 * LoginService loginService=new LoginServiceImpl(); //对比
		 * if(loginService.login(user)=="login_s.jsp") { HttpSession
		 * session=req.getSession(); session.setAttribute("username", username);
		 * resp.sendRedirect("login_s.jsp"); }else {
		 * resp.sendRedirect("login_pwrong.jsp"); }
		 * 
		 * }else if(action.equals("register")) { Connection
		 * conn=ConnectionUtil.getConnection(); PreparedStatement ps=null; try {
		 * ps=conn.prepareStatement("insert into user(username,password) values(?,?)");
		 * ps.setString(1, username); ps.setString(2, password); int
		 * i=ps.executeUpdate(); if(i==1) { resp.sendRedirect("register_s.jsp"); }else {
		 * resp.sendRedirect("register_f.jsp"); } } catch (SQLException e) {
		 * resp.sendRedirect("register_f.jsp"); e.printStackTrace(); }finally {
		 * if(ps!=null) { try { ps.close(); } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } } } }
		 */


	}
	}
