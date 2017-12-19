package com.gpf.esoracle;

import java.io.IOException;
import java.sql.SQLException;

public class JDBCTest {
	public static void main(String[] args) {
		try {
			long starttime=System.currentTimeMillis();
			//将数据从 T_SB_ZWYC_GT 中 查询 442552条数据 以json格式存放到 d://data_gt2.json中   存储到kibana中为索引为cxtj_gt  类型为gt
			JDBCUtil.readDataByPage("t_sb_zwyc_xl", 2000, "D://data_dxd2.json",
					"cxtj_gt111", "gt111",1);
			long endTime=System.currentTimeMillis();
			
			System.out.println("花费时间为:" + (endTime - starttime));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "bb");
        map.put("2", "cc");
        map.put("3", "dd");
        JSONObject jsonObject = JSONObject.fromObject(map);
        System.out.println(jsonObject);
    }*/
}
