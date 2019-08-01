package database;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import bean.ImageBean;

public class CheckServlet implements javax.servlet.Servlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception{

        final int IMAGENUM = 9;
        String userId =request.getParameter("uid");
        int imageId = Integer.parseInt(request.getParameter("id"));
        //String imageName = imageId+".jpg";
        int lowerL = Integer.parseInt(request.getParameter("l"));
        int upperL = Integer.parseInt(request.getParameter("u"));

        int resolution = (lowerL + upperL)/2;
        int bodyShape = Integer.parseInt(request.getParameter("body_shape"));
        int skinColor = Integer.parseInt(request.getParameter("skin_color"));
        int adlType = Integer.parseInt(request.getParameter("adl_type"));
        int hairColor = Integer.parseInt(request.getParameter("hair_color"));
        int gender = Integer.parseInt(request.getParameter("gender"));
        int age = Integer.parseInt(request.getParameter("age"));
        int identity = Integer.parseInt(request.getParameter("identity"));
        DBOperation cs = new DBOperation();
        boolean canIdentify = cs.check(imageId+".jpg",bodyShape,skinColor,adlType,hairColor,gender,age,identity);
        String url = null;
        System.out.println("lowerL: "+lowerL + "\tupperL: "+upperL);



        if(canIdentify){
            if((upperL-lowerL)<=1){
                System.out.println("successfully identify resolution");
                cs.insertData(userId, imageId, lowerL, upperL, 1, 1);

                if(Math.random()>0.5){
                    lowerL = (int)(8+ Math.random()*4-2);
                    upperL = 264-lowerL;
                    url = String.format("display.jsp?uid=%s&id=%d&l=%d&u=%d", userId, imageId, lowerL, upperL);
                    response.sendRedirect(url);
                }else{
                    response.sendRedirect("attentionCheck.jsp");
                }




            }
            else{
                System.out.println("can identify");
                cs.insertData(userId, imageId, lowerL, upperL, 1, 0);
                upperL = resolution;
                //new ImageBean().generateImage(imageId, (lowerL+upperL)/2);
                url = String.format("display.jsp?uid=%s&id=%d&l=%d&u=%d", userId, imageId, lowerL, upperL);
                response.sendRedirect(url);
            }

        }else{
            if((upperL-lowerL)<=1){
                System.out.println("fail to identify the pic");
                cs.insertData(userId, imageId, lowerL, upperL, 0, -1);
                //出题
                lowerL = (int)(8+ Math.random()*4-2);
                upperL = 264-lowerL;
                url = String.format("display.jsp?uid=%s&id=%d&l=%d&u=%d", userId, (int)(Math.random()*IMAGENUM+1), lowerL, upperL);
                response.sendRedirect(url);

            }else{
                System.out.println("can't identify");
                cs.insertData(userId, imageId, lowerL, upperL, 0, 0);
                lowerL = resolution;
                //new ImageBean().generateImage(imageId, (lowerL+upperL)/2);
                url = String.format("display.jsp?uid=%s&id=%d&l=%d&u=%d", userId, imageId, lowerL, upperL);
                response.sendRedirect(url);
            }

        }

        //new ImageBean().generateImage(imageId, (lowerL+upperL)/2);
        //url = String.format("display.jsp?id=%d&l=%d&u=%d", imageId, lowerL, upperL);
        //response.sendRedirect(url);


    }



    public void destroy() {
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public String getServletInfo() {
        return null;
    }

    public void init(ServletConfig arg0) throws ServletException {
    }

    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        HttpServletRequest rq = (HttpServletRequest) request;
        HttpServletResponse rs = (HttpServletResponse) response;
        try {
            doPost(rq, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
