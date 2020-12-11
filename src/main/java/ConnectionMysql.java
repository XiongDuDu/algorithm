import com.sun.deploy.util.StringUtils;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author duwei7
 * @time 2020/10/14 19:49
 */
public class ConnectionMysql {

    //MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "rootroot";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            List<String> c1s = new ArrayList<>();
            for(int i = 0; i<= 10000; ++i) {
                c1s.add(i+"");
            }
            stmt = conn.createStatement();
            String sql;
            sql = "select c1 from test_milli WHERE c1 in (" + StringUtils.join(c1s, ",") + ");";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            System.out.println("total " + c1s.size());
            Long time1 = System.currentTimeMillis();
            while(rs.next()){
                // 通过字段检索
                int c1  = rs.getInt("c1");
                // 输出数据
//                System.out.print("c1: " + c1);
            }
            System.out.println("只查询一次 time is " + (System.currentTimeMillis() - time1));
            Long time2 = System.currentTimeMillis();
            Statement finalStmt = stmt;
            for (String c1 : c1s) {
                rs = stmt.executeQuery("select c1 from test_milli WHERE c1 in (" + c1 + ");");
                while(rs.next()){
                    rs.getInt("c1");
                }
            }
            System.out.println("一条一条查询 time is " + (System.currentTimeMillis() - time2));
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) {
                    stmt.close();
                }
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) {
                    conn.close();
                }
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");


        System.out.println(formatCurrentTimeMin(System.currentTimeMillis() / 1000));
    }
    public static Long getTimeBeforeOneHour(Long time) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHH");
        return Long.parseLong(dateFormat.format(getCalendar(time, 0).getTime()));
    }

    public static Calendar getCalendar(Long time, int n) {
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHH");
        try {
            date = dateFormat.parse(time + "");
        } catch (Exception e) {
        }
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - n);
        return calendar;
    }

    public static Long formatCurrentTimeMin(Long time) {
        time = time * 1000;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        return Long.parseLong(sdf.format(new Date(time)));
    }

}