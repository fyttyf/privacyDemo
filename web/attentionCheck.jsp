<%--
  Created by IntelliJ IDEA.
  User: fengyutian
  Date: 2019-07-25
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page import= "java.util.*" import="java.text.*" isThreadSafe="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Attention Check</title>

    <%
        response.setHeader("refresh","30");
    %>
</head>
<body>
<%!
    int imageId;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>

<%
    imageId = (int)(Math.random()*2+1);
    Date finishDate = (Date)session.getAttribute("finishDate");
    if(finishDate==null){
        finishDate = new Date(new Date().getTime()+300000);
        session.setAttribute("finishDate", finishDate);
        //System.out.println("impossible");
        //System.exit(-1);
    }else{
        Date nowDate = new Date();
        if(finishDate.compareTo(nowDate)<0){
            response.sendRedirect("fail.jsp");
        }

    }

%>

<%= sdf.format(finishDate) %><br>
<%= sdf.format(new Date()) %><br>

<img src= <%=String.format("image/attentionCheck/%d.jpg", imageId) %> width="256" height="356" >

<form action= <%= String.format("attentionCheck?id=%d", imageId)%> method="post">
    How many people are there in the picture?
    &nbsp<input type="radio" name="num" value="1">1
    &nbsp<input type="radio" name="num" value="2">2
    &nbsp<input type="radio" name="num" value="3">3<br>
    <input type="submit" value="Submit">&nbsp<input type="reset" value="Reset">
</form>




</body>
</html>
