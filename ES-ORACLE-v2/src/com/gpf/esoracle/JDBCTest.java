package com.gpf.esoracle;

import java.io.IOException;
import java.sql.SQLException;

public class JDBCTest {
	public static void main(String[] args) {
		try {
			long starttime=System.currentTimeMillis();
			//�����ݴ� T_SB_ZWYC_GT �� ��ѯ 442552������ ��json��ʽ��ŵ� d://data_gt2.json��   �洢��kibana��Ϊ����Ϊcxtj_gt  ����Ϊgt
			/*JDBCUtil.readDataByPage("t_sb_zwyc_xl", 2000, "D://data_dxd2.json",
					"cxtj_gt111", "gt111",1);*/
			JDBCUtil.readDataByPageThread("t_sb_znyc_dz", 2000, "D://data_dxd2.json",
					"cxtj_gt", "gt",1);
			long endTime=System.currentTimeMillis();
			
			System.out.println("����ʱ��Ϊ:" + (endTime - starttime));
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
