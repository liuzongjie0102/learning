package bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class SpiltPage {
    //定义数据库连接对象和结果集对象
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private ResultSetMetaData rsmd = null;
    //SQL查询语句
    private String sqlStr;
    //总记录数目
    private int rowCount = 0;
    //所分的逻辑页数
    private int pageCount = 0;
    //每页显示的记录数目
    private int pageSize = 0;
    //设置参数值
    public void setCon(Connection con) {
        this.con = con;
        if(null==this.con) {
            System.out.println("Failure to get a connection");
        }else {
            System.out.println("Success to get a connection");
        }
    }

    //初始化数据库表中的信息
    public void initialize(String sqlStr,int pageSize,int ipage) {
        int irows = pageSize*(ipage-1);
        this.sqlStr = sqlStr;
        this.pageSize = pageSize;
        try {
            long star = System.currentTimeMillis();
            stmt = this.con.createStatement();
            con.prepareStatement(sqlStr);
            rs = stmt.executeQuery(this.sqlStr);
            if(null != rs) {
                rs.last();
                this.rowCount = rs.getRow();
                rs.first();
                this.pageCount = (this.rowCount-1)/this.pageSize+1;
            }
            this.sqlStr = sqlStr+" limit "+irows+","+pageSize;
            stmt = this.con.createStatement();
            rs = stmt.executeQuery(this.sqlStr);
            rsmd = rs.getMetaData();
            System.out.println("useTime:" + (System.currentTimeMillis() - star));

        }catch(SQLException e) {
            System.out.println("zcn数据库异常："+e.toString());
        }
    }

    //将显示结果存到Vector
    public Vector getPage() {
        Vector vData = new Vector();
        try {
            if(null != rs) {
                while(rs.next()) {
                    String[] sData = new String[4];
                    for(int j=0;j<rsmd.getColumnCount();j++) {
                        sData[j] = rs.getString(j+1);
                    }
                    vData.addElement(sData);
                }
            }
            rs.close();
            stmt.close();
        }catch(SQLException e) {
            System.out.println("zcn数据库异常："+e.toString());
        }
        return vData;
    }

    //获得页面总数
    public int getPageCount() {
        return this.pageCount;
    }

    //获取数据表
    public int getRowCount() {
        return this.rowCount;
    }
}