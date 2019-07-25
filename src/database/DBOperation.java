package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class DBOperation {
    public void insertData(String userId, int imageId,
                           int lowerL, int upperL, int isIdentify, int isSuccess){


        Connection conn = null;

        try{
            conn = DBConnection.getConnection();
            PreparedStatement psql = conn.prepareStatement("insert into logInfo (id,imageId,lowerL,upperL,isIdentify, isSuccess)"+"values(?,?,?,?,?,?)");  //用preparedStatement预处理来执行sql语句
            psql.setString(1, userId);
            psql.setInt(2, imageId);
            psql.setInt(3, lowerL);
            psql.setInt(4, upperL);
            psql.setInt(5, isIdentify);
            psql.setInt(6,isSuccess);
            //psql.executeUpdate();  //参数准备后执行语句
            psql.close();
        }catch(SQLException e){
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            System.out.println("数据库数据插入成功！"+"\n");
        }


    }


    public boolean check(String imageName, int bodyShape, int skinColor,
                        int adlType, int hairColor, int gender, int age, int identity) throws Exception{
        boolean returnValue = false;
        String sql = "SELECT * FROM imageInfo";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        //System.out.println("###########");
        try {

            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            //System.out.println("HERE@@@@@@@");
            while (rs.next()) {

                String name = rs.getString("name");
                int body_shape = rs.getInt("body_shape");


                //todo
                if (name.equals(imageName) && bodyShape==body_shape) {
                    //   如果用户名和密码都和数据库的一样，就返回true
                    returnValue = true;
                    break;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue;

    }


    public boolean checkAttention(int imageId, int option) throws Exception{
        boolean returnValue = false;
        String sql = "SELECT * FROM attentionInfo";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        //System.out.println("###########");
        try {

            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            //System.out.println("HERE@@@@@@@");
            while (rs.next()) {

                int image = rs.getInt("imageId");
                int ans = rs.getInt("answer");


                //todo
                if (imageId==image && option==ans) {
                    //   如果用户名和密码都和数据库的一样，就返回true
                    returnValue = true;
                    break;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue;

    }

    public static void main(String[] args){
        DBOperation l = new DBOperation();
        try{
            //l.insertData("00000", 1,8,252,1,0);
            System.out.println(l.checkAttention(1,3));
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
