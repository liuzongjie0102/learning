<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@page import="java.sql.*" %>
<%@page import="java.io.*" %>
<%@page import="java.util.*" %>
<%@page import="bean.*"%>
<jsp:useBean id="pages" scope="page" class="bean.SpiltPage"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%!
    //每页显示的记录数
    int pageSize = 20;
    String sqlStr="";
    //当前页
    int showPage=1;
    //数据库用户名
    String userName="lzj";
    //数据库密码
    String userPassword="123456";
    //数据库的URL,包括连接数据库所使用的编码格式
    String url="jdbc:mysql://localhost:3306/app";
    //定义连接对象
    Connection dbcon;
%>
<%
    try{
        //加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //获得数据库的连接对象
        dbcon = DriverManager.getConnection(url,userName,userPassword);
    }catch(SQLException e){
        //打印出异常信息
        System.out.println(e.toString());
    }

    //给pages中参数con赋值
    pages.setCon(dbcon);
    sqlStr="select t2.user_name, t3.sub_name, t1.SUB_SCORE from score t1"
            + "    inner join student t2 on t1.USER_ID = t2.user_id"
            + "    inner join subject t3 on t1.SUB_ID = t3.sub_id";
    //查询数据表，获得查询结果
    String strPage = null;
    //获取跳转到的目的的页面
    strPage = request.getParameter("showPage");
    if(null == strPage){
        showPage=1;
    }else{
        try{
            showPage=Integer.parseInt(strPage);
        }catch(NumberFormatException e){
            showPage = 1;
        }

        if(showPage<1){
            showPage=1;
        }
    }

    pages.initialize(sqlStr, pageSize, showPage);
    Vector vData = pages.getPage();
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>分页显示</title>
</head>
<body bgcolor="#FFFFFF" text="#000000">
<h1 align=center>成绩单</h1>
<div align=center>
    <table border="1" cellspacing="0" cellpadding="0" width="80%">
        <tr>
            <th width="20%">姓名</th>
            <th width="50%">学科</th>
            <th width="50%">分数</th>
        </tr>
        <%
            for(int i=0;i<vData.size();i++){
                //显示数据
                String[] sData=(String[])vData.get(i);
        %>
        <tr>
            <td><%=sData[0]%></td>
            <td align=left><%=sData[1]%></td>
            <td align=left><%=sData[2]%></td>
        </tr>
        <%}%>
    </table>
    <form action="query.jsp" mehod="get" target="_self">
        共<font color=red><%=pages.getRowCount()%></font>条&nbsp;
        <%=pageSize%>条/页&nbsp;
        第<font color=red><%=showPage%></font>页/共
        <font color=red><%=pages.getPageCount()%></font>页&nbsp;
        <a href="query.jsp?showPage=1" target="_self">[首页]</a>&nbsp;
        <%
            //判断“上一页”链接是否要显示
            if(showPage>1){
        %>
        <a href="query.jsp?showPage=<%=showPage-1%>"target="_self">[上一页]</a>&nbsp;
        <%
        }else{
        %>
        [上一页]&nbsp;
        <%
            }
            //判断下一页链接是否显示
            if(showPage<pages.getPageCount()){
        %>
        <a href="query.jsp?showPage=<%=showPage+1%>"target="_self">[下一页]</a>
        <%
        }else{
        %>
        [下一页]&nbsp;
        <%} %>
        <a href="query.jsp?showPage=<%=pages.getPageCount()%>"target="_self">[尾页]</a>&nbsp;
        转到
        <select name="showPage">
            <%
                for(int i=1;i<=pages.getPageCount();i++){
            %>
            <option value="<%=i%>"<%if(showPage==i)out.println("selected");%>><%=i%></option>
            <%} %>
        </select>
        页&nbsp;
        <input type="submit" name="go" value="提交"/>
    </form>
    <%
        //关闭数据库连接
        dbcon.close();
    %>
</div>
</body>
</html>