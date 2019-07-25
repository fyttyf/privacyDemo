<%--
  Created by IntelliJ IDEA.
  User: fengyutia
  Date: 2019-07-12
  Time: 09:05
  To change this template use File | Settings | File Templates.

  平台能保证用户只能访问问卷一次吗

  我不知道 多用户同时访问会出现什么问题


  session


  //id imageID l u 是否能分辨 是否分辨结束

--%>
<%@ page import= "java.util.*" import="java.text.*" isThreadSafe="true" contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="image" scope="request" class="bean.ImageBean"/>

<html>
<head>
    <title>Privacy Protection</title>
    <script>
        function validate(){
            return checkRadio(document.getElementsByName("body_shape"))
                && checkRadio(document.getElementsByName("skin_color"))
                && checkRadio(document.getElementsByName("adl_type"))
                && checkRadio(document.getElementsByName("hair_color"))
                && checkRadio(document.getElementsByName("gender"))
                && checkRadio(document.getElementsByName("age"))
                && checkRadio(document.getElementsByName("identity"));
        }


        function checkRadio(objRadio) {
            for(i = 0; i < objRadio.length; i++) {
                if(objRadio[i].checked) {
                    return true;
                }
            }
            return false;
        }



    </script>

    <%
        response.setHeader("refresh","30");
    %>
</head>
<body>
<%!
    //improvement can be added into session
    //每个用户登陆的时候 初始化
    int imageId = (int)(Math.random()*2+1); //读入要显示的图片和图片的分辨率
    int lowerL = (int)(8+ Math.random()*4-2);
    int upperL = 264-lowerL;
    //初始的照片 132
    int resolution;
    //Date now = new Date();
    //Date finishDate = new Date(now.getTime()+300000);
    //double time = Math.random();//截止时间 倒计时 时间到了 直接结束

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


%>
<%
    //todo: 改成session
    if(request.getParameter("id")!=null){
        imageId = Integer.parseInt(request.getParameter("id"));
        lowerL = Integer.parseInt(request.getParameter("l"));
        upperL = Integer.parseInt(request.getParameter("u"));
    }
    //定义函数生成不同分辨率的图像
    //用一个javabean也行

    resolution = (lowerL + upperL)/2;

    Date finishDate = (Date)session.getAttribute("finishDate");
    if(finishDate==null){
        finishDate = new Date(new Date().getTime()+300000);
        session.setAttribute("finishDate", finishDate);
    }else{
        Date nowDate = new Date();
        if(finishDate.compareTo(nowDate)<0){
            response.sendRedirect("fail.jsp");
        }

    }

%>

<%= sdf.format(finishDate) %><br>
<%= sdf.format(new Date()) %><br>




<!-- 调用函数 对imageName生成resolution的图像-->
<img src= <%=String.format("image/tmp/%d_%d.jpg", imageId, resolution)%> >

<%= String.format("image/tmp/%d_%d.jpg", imageId, resolution) %><br>
<%-- System.out.printf("%d_%d.jpg\n", imageId, resolution); --%>
<!--"check?id=1&res=1"我觉得应该要用javabean-->
<form action=<%=String.format("check?id=%d&l=%d&u=%d", imageId, lowerL, upperL) %> method="post" onSubmit="return validate()">
    1. Body Shape
    &nbsp<input type="radio" name="body_shape" value="1">Small
    &nbsp<input type="radio" name="body_shape" value="2">Medium
    &nbsp<input type="radio" name="body_shape" value="3">Large
    <br>
    2. Skin Color
    &nbsp<input type="radio" name="skin_color" value="1">White
    &nbsp<input type="radio" name="skin_color" value="2">Yellow
    &nbsp<input type="radio" name="skin_color" value="3">Black
    <br>
    3. ADL type
    &nbsp<input type="radio" name="adl_type" value="1">type1
    &nbsp<input type="radio" name="adl_type" value="2">type2
    &nbsp<input type="radio" name="adl_type" value="3">type3
    &nbsp<input type="radio" name="adl_type" value="4">type4
    &nbsp<input type="radio" name="adl_type" value="5">type5
    &nbsp<input type="radio" name="adl_type" value="6">type6
    <br>
    4. Hair Color
    &nbsp<input type="radio" name="hair_color" value="1">color1
    &nbsp<input type="radio" name="hair_color" value="2">color2
    &nbsp<input type="radio" name="hair_color" value="3">color3
    <br>
    5. Gender
    &nbsp<input type="radio" name="gender" value="1">male
    &nbsp<input type="radio" name="gender" value="2">female
    <br>
    6. Age
    &nbsp<input type="radio" name="age" value="1">period1
    &nbsp<input type="radio" name="age" value="2">period2
    &nbsp<input type="radio" name="age" value="3">period3
    <br>
    7. Identity
    &nbsp<input type="radio" name="identity" value="1">identity1
    &nbsp<input type="radio" name="identity" value="2">identity2
    <br>
    <input type="submit" value="Submit">&nbsp<input type="reset" value="Reset">
</form>

<!--
<img src= width=20px height=20px>
-->
</body>
</html>
