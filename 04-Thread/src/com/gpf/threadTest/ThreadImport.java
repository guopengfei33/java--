package com.gpf.threadTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class ThreadImport {
	private String url="jdbc:oracle:thin:@172.16.221.225:1521:wszc";
    private String user="test";
    private String password="test_1117";
    public Connection getConnect(){
        Connection con = null;
         try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
         return con;
    }
    public void multiThreadImport( final int ThreadNum){
        final CountDownLatch cdl= new CountDownLatch(ThreadNum);
        long starttime=System.currentTimeMillis();
        int flag = 800000/ThreadNum;
        for(int k=1;k<=ThreadNum;k++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Connection con=getConnect();
                    try {
                        Statement st=con.createStatement();
                        
                        for(int i=1;i<=flag;i++){
                        	System.out.println("i="+i + " ------------" + this);
                            String uuid=UUID.randomUUID().toString();
                            System.out.println("i="+uuid);
                            // 目前列很短只能插4位以下
                            //st.addBatch("insert into test_1117(id,name) values('"+132+"','"+23+"')");
                           //String sql = "insert into T_YD_BYQ_JRD (OPER_ID, JOIN_ID_GIS, JOIN_ID, JOIN_NAME, TRAN_ID_GIS, TRAN_ID, TRAN_NAME, TRAN_TYPE, TRAN_TABLE, CHANGE_TYPE, WRITE_DATE, READ_DATE, READ_FLAG, MEMO)values ('"+1232354346+"', '271', '4AE7F2A0C7394438907EC32E1F3AC2205101', '前华村(南尾)68号表箱', '3512411', '094eff95-21fa-4c42-98e1-47e2f7bb23d1', '前华村(南尾)(400总开)', '03', '01', '01', to_date('02-09-2016 10:55:43', 'dd-mm-yyyy hh24:mi:ss'), null, '0', null)";
                           String sql1 = "insert into T_YD_BYQ_JRDTEST (OPER_ID, JOIN_ID_GIS, JOIN_ID, JOIN_NAME, TRAN_ID_GIS, TRAN_ID, TRAN_NAME, TRAN_TYPE, TRAN_TABLE, CHANGE_TYPE, WRITE_DATE, READ_DATE, READ_FLAG, MEMO)values ('"+uuid+"', '271', '4AE7F2A0C7394438907EC32E1F3AC2205101', '前华村(南尾)68号表箱', '3512411', '094eff95-21fa-4c42-98e1-47e2f7bb23d1', '前华村(南尾)(400总开)', '03', '01', '01', to_date('02-09-2016 10:55:43', 'dd-mm-yyyy hh24:mi:ss'), null, '0', null)";
                           String sql2 = "insert into T_YD_BYQ_JRDTEST2 (OPER_ID, JOIN_ID_GIS, JOIN_ID, JOIN_NAME, TRAN_ID_GIS, TRAN_ID, TRAN_NAME, TRAN_TYPE, TRAN_TABLE, CHANGE_TYPE, WRITE_DATE, READ_DATE, READ_FLAG, MEMO)values ('"+uuid+"', '271', '4AE7F2A0C7394438907EC32E1F3AC2205101', '前华村(南尾)68号表箱', '3512411', '094eff95-21fa-4c42-98e1-47e2f7bb23d1', '前华村(南尾)(400总开)', '03', '01', '01', to_date('02-09-2016 10:55:43', 'dd-mm-yyyy hh24:mi:ss'), null, '0', null)";
                          // String sql3 = "insert into T_YD_BYQ_JRD3 (OPER_ID, JOIN_ID_GIS, JOIN_ID, JOIN_NAME, TRAN_ID_GIS, TRAN_ID, TRAN_NAME, TRAN_TYPE, TRAN_TABLE, CHANGE_TYPE, WRITE_DATE, READ_DATE, READ_FLAG, MEMO)values ('"+1232354346+"', '271', '4AE7F2A0C7394438907EC32E1F3AC2205101', '前华村(南尾)68号表箱', '3512411', '094eff95-21fa-4c42-98e1-47e2f7bb23d1', '前华村(南尾)(400总开)', '03', '01', '01', to_date('02-09-2016 10:55:43', 'dd-mm-yyyy hh24:mi:ss'), null, '0', null)";
                          // String sql4 = "insert into T_YD_BYQ_JRD4 (OPER_ID, JOIN_ID_GIS, JOIN_ID, JOIN_NAME, TRAN_ID_GIS, TRAN_ID, TRAN_NAME, TRAN_TYPE, TRAN_TABLE, CHANGE_TYPE, WRITE_DATE, READ_DATE, READ_FLAG, MEMO)values ('"+1232354346+"', '271', '4AE7F2A0C7394438907EC32E1F3AC2205101', '前华村(南尾)68号表箱', '3512411', '094eff95-21fa-4c42-98e1-47e2f7bb23d1', '前华村(南尾)(400总开)', '03', '01', '01', to_date('02-09-2016 10:55:43', 'dd-mm-yyyy hh24:mi:ss'), null, '0', null)";
                          // String sql5 = "insert into T_YD_BYQ_JRD5 (OPER_ID, JOIN_ID_GIS, JOIN_ID, JOIN_NAME, TRAN_ID_GIS, TRAN_ID, TRAN_NAME, TRAN_TYPE, TRAN_TABLE, CHANGE_TYPE, WRITE_DATE, READ_DATE, READ_FLAG, MEMO)values ('"+1232354346+"', '271', '4AE7F2A0C7394438907EC32E1F3AC2205101', '前华村(南尾)68号表箱', '3512411', '094eff95-21fa-4c42-98e1-47e2f7bb23d1', '前华村(南尾)(400总开)', '03', '01', '01', to_date('02-09-2016 10:55:43', 'dd-mm-yyyy hh24:mi:ss'), null, '0', null)";
                          // String sql6 = "insert into T_YD_BYQ_JRD6 (OPER_ID, JOIN_ID_GIS, JOIN_ID, JOIN_NAME, TRAN_ID_GIS, TRAN_ID, TRAN_NAME, TRAN_TYPE, TRAN_TABLE, CHANGE_TYPE, WRITE_DATE, READ_DATE, READ_FLAG, MEMO)values ('"+1232354346+"', '271', '4AE7F2A0C7394438907EC32E1F3AC2205101', '前华村(南尾)68号表箱', '3512411', '094eff95-21fa-4c42-98e1-47e2f7bb23d1', '前华村(南尾)(400总开)', '03', '01', '01', to_date('02-09-2016 10:55:43', 'dd-mm-yyyy hh24:mi:ss'), null, '0', null)";
                          // String sql7 = "insert into T_YD_BYQ_JRD7 (OPER_ID, JOIN_ID_GIS, JOIN_ID, JOIN_NAME, TRAN_ID_GIS, TRAN_ID, TRAN_NAME, TRAN_TYPE, TRAN_TABLE, CHANGE_TYPE, WRITE_DATE, READ_DATE, READ_FLAG, MEMO)values ('"+1232354346+"', '271', '4AE7F2A0C7394438907EC32E1F3AC2205101', '前华村(南尾)68号表箱', '3512411', '094eff95-21fa-4c42-98e1-47e2f7bb23d1', '前华村(南尾)(400总开)', '03', '01', '01', to_date('02-09-2016 10:55:43', 'dd-mm-yyyy hh24:mi:ss'), null, '0', null)";
                          // String sql8 = "insert into T_YD_BYQ_JRD8 (OPER_ID, JOIN_ID_GIS, JOIN_ID, JOIN_NAME, TRAN_ID_GIS, TRAN_ID, TRAN_NAME, TRAN_TYPE, TRAN_TABLE, CHANGE_TYPE, WRITE_DATE, READ_DATE, READ_FLAG, MEMO)values ('"+1232354346+"', '271', '4AE7F2A0C7394438907EC32E1F3AC2205101', '前华村(南尾)68号表箱', '3512411', '094eff95-21fa-4c42-98e1-47e2f7bb23d1', '前华村(南尾)(400总开)', '03', '01', '01', to_date('02-09-2016 10:55:43', 'dd-mm-yyyy hh24:mi:ss'), null, '0', null)";
                           //String sql9 = "insert into T_YD_BYQ_JRD9 (OPER_ID, JOIN_ID_GIS, JOIN_ID, JOIN_NAME, TRAN_ID_GIS, TRAN_ID, TRAN_NAME, TRAN_TYPE, TRAN_TABLE, CHANGE_TYPE, WRITE_DATE, READ_DATE, READ_FLAG, MEMO)values ('"+1232354346+"', '271', '4AE7F2A0C7394438907EC32E1F3AC2205101', '前华村(南尾)68号表箱', '3512411', '094eff95-21fa-4c42-98e1-47e2f7bb23d1', '前华村(南尾)(400总开)', '03', '01', '01', to_date('02-09-2016 10:55:43', 'dd-mm-yyyy hh24:mi:ss'), null, '0', null)";                   
                           st.addBatch(sql1);
                           st.addBatch(sql2);
//                           st.addBatch(sql3); 
//                           st.addBatch(sql4);
//                           st.addBatch(sql5);
//                           st.addBatch(sql6);
//                           st.addBatch(sql7);
//                           st.addBatch(sql8); 
//                           st.addBatch(sql9);
//                           st.addBatch(sql);
//                           System.out.println("sql="+sql);  
//                           System.out.println("sql="+sql1); 
//                           System.out.println("sql="+sql2); 
//                           System.out.println("sql="+sql3); 
//                           System.out.println("sql="+sql4); 
//                           System.out.println("sql="+sql5); 
//                           System.out.println("sql="+sql6);  
//                           System.out.println("sql="+sql7); 
//                           System.out.println("sql="+sql8); 
//                           System.out.println("sql="+sql9); 
                            if(i%10==0){
                                st.executeBatch();
                                System.out.println("st.executeBatch="+st);
                            }
                        }
                        cdl.countDown();
                        System.out.print("i=");
                    } catch (Exception e) {
                    	e.printStackTrace();
                    }finally{
                        try {
                            con.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        try {
            cdl.await();
            long spendtime=System.currentTimeMillis()-starttime;
            System.out.println( ThreadNum+"个线程花费时间:"+spendtime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ThreadImport ti=new ThreadImport();
//        ti.multiThreadImport(1);
//        ti.multiThreadImport(5);
//        ti.multiThreadImport(8);
//        ti.multiThreadImport(10);
//        ti.multiThreadImport(20);
       ti.multiThreadImport(40);
        System.out.println("笔记本CPU数:"+Runtime.getRuntime().availableProcessors());
    }


}
