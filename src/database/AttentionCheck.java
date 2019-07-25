package database;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AttentionCheck implements javax.servlet.Servlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception{
        int imageId = Integer.parseInt(request.getParameter("id"));
        int ans = Integer.parseInt(request.getParameter("num"));
        String userId = "00000";

        DBOperation cs = new DBOperation();
        boolean canIdentify = cs.checkAttention(imageId, ans);
        cs.insertData(userId, -1*imageId, 0, 0, (canIdentify)?1:0, 0);
        if(canIdentify){
            int lowerL = (int)(8+ Math.random()*4-2);
            int upperL = 264-lowerL;
            String url = String.format("display.jsp?id=%d&l=%d&u=%d", imageId, lowerL, upperL);
            response.sendRedirect(url);
        }else{
            response.sendRedirect("fail.jsp");
        }

        //设定一个threshold 如果做错两次 就fail

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
