package com;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.*;

public class main {
    public static void main(String[] args){
        testHvie();
    }


    public static void testDb2(){
        Connection conn = null;
        Statement pstmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            conn = DriverManager.getConnection("jdbc:db2://10.1.0.157:50000/nlxjdb","db2inst1","oIr@njKK");
            pstmt = conn.createStatement();
            rs = (ResultSet) pstmt.executeQuery("select * from test_lzj_0819");
            System.out.println("userId, userName");
            while (rs.next()){
                int userId = rs.getInt("ID");
                String userName = rs.getString("NAME");
                System.out.println(userId + ", " + userName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void testGp(){
        Connection conn = null;
        Statement pstmt = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:pivotal:greenplum://10.1.8.3:5432;DatabaseName=newbiweb","udapdev","udap");
            pstmt = conn.createStatement();
            ResultSet rs = pstmt.executeQuery("select * from temp_test");
            System.out.println("name");
            while (rs.next()){
                String userName = rs.getString("name");
                System.out.println(userName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static void testOracle(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@10.1.0.242:1521:ywxx","newbiweb","I%qAiU#z$4");
            pstmt = conn.prepareStatement("insert into test_lzj_3 values(to_date(?,'yyyy-mm-dd hh24:mi:ss'),?)");
            pstmt.setString(1,"2019-09-02 17:45:30");
            pstmt.setString(2,"1");
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void testHvie(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet result = null;
        try {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
            conn = DriverManager.getConnection("jdbc:hive2://10.1.8.3:10000/hive_qzyys_dev","app", "app");
            result = stmt.executeQuery("desc test_lzj");

            while(result.next()) {
                String columnName = result.getString(1);
                String columnType = result.getString(2);

                if (!columnName.contains("#")) {
                    System.out.println(columnName + " " + columnType);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void JdbcPool(){


        BasicDataSource dbcp = new BasicDataSource();
        dbcp.setUrl("jdbc:db2://10.1.0.157:50000/nlxjdb");
        dbcp.setUsername("db2inst1");
        dbcp.setPassword("oIr@njKK");
        dbcp.setDriverClassName("com.ibm.db2.jcc.DB2Driver");
        dbcp.setMaxActive(1);

        try {
            Connection conn = dbcp.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select * from temp_test");
            System.out.println("userId, userName");
            while (rs.next()){
                String userId = rs.getString("ID");
                String userName = rs.getString("NAME");
                System.out.println(userId + ", " + userName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
