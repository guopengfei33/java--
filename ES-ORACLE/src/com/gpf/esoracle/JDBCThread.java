package com.gpf.esoracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 为 JDBC 的多线程查询数据
 * 
 * @author 郭鹏飞
 * @since 2017/12/18 15.27
 *
 */
public class JDBCThread {

	/*
	 * 定义一个方法进行
	 */
	public static void muilQueryForThread(final String tablename, final int num, final int page) {
		// 进行条数的获取
		final int start = num * page;
		final int end = (num + 1) * page;
		new Thread(new Runnable() { // 内部类
					public void run() {
						// 获取到数据库的连接
						Connection connection = JDBCUtil.jdbcCon();
						//
						if (connection != null) {
							Long startTime = System.currentTimeMillis();// 开始时间
							// 执行查询
							PreparedStatement sta;
							try {
								sta = connection
										.prepareStatement("select * from(select rownum as rn,t.obj_id,t.sbbm,t.ssds,t.ywdw from "
												+ tablename
												+ " t ) where rn >= "
												+ start + " and rn < " + end);

								ResultSet rs = sta.executeQuery();
								// 获取数据列表
								List<Map> data = new ArrayList();
								// 获取到列名
								List<String> columnLabels = JDBCUtil
										.getColumnLabels(rs);
								
								// 进行数据的封装
								Map<String, Object> map = null;
								while (rs.next()) {
									map = new HashMap<String, Object>();

									for (String columnLabel : columnLabels) {
										Object value = rs
												.getObject(columnLabel);
										map.put(columnLabel.toLowerCase(),
												value);
									}
									data.add(map);
								}
								System.out.println("map :" + data.size() + "this----" + this);
								sta.close();
								connection.close();
								Long end = System.currentTimeMillis();
								System.out.println( this + " -------- " + "   开始时间:"
										+ startTime + "  结束时间:" + end + "  用时:"
										+ (end - startTime) + "ms");
							} catch (SQLException e) {
								e.printStackTrace();
							}

						}
					}
				}).start();
	}
}
