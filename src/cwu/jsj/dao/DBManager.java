package cwu.jsj.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/*<1> 私有化DBManager的构造函数，定义一个静态的成员变量，在一个共有方法中实例化该成员变量。若要实例化对象调用此方法即可。

同一时间该类只能存在一个对象。符合sql对象习惯。 （此方式有缺陷，具体自行搜索）　　　　　　　

<2> 定义数据库连接、关闭以及增删改查的基本操作，返回结果集。*/

public class DBManager {
	// 数据库连接常量
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String USER = "root";
    public static final String PASS = "root";
    public static final String URL = "jdbc:mysql://localhost:3306/management?useUnicode=true&characterEncoding=UTF-8"; //127.0.0.1

    // 静态成员，支持单态模式
    private static DBManager per = null;
    private Connection conn = null;
    private Statement stmt = null;

    // 单态模式-懒汉模式
    private DBManager() {
    }

    public static DBManager createInstance() {
        if (per == null) {
            per = new DBManager();
            per.initDB();
        }
        return per;
    }

    // 加载驱动
    public void initDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 连接数据库，获取句柄+对象
    public void connectDB() {
        System.out.println("Connecting to database...");
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("SqlManager:Connect to database successful.");
    }
    
 // 连接数据库，获取句柄+对象
    public Connection connectDB2() {
        System.out.println("Connecting to database...");
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            //stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("SqlManager:Connect to database successful.");
        return conn;
    }
    
    

    // 关闭数据库 关闭对象，释放句柄
    public void closeDB() {
        System.out.println("Close connection to database..");
        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Close connection successful");
    }

  //对数据库的查询操作
    public ResultSet executeQuery(String sql){
    	ResultSet rs;
    	try{
    	if(conn==null){
    		System.out.println("conn为null");
    		connectDB();
    	}
    		Statement stmt=conn.createStatement();//得到结果集，用来遍历
    	
    	
    	try {
			rs=stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("执行query语句时执行失败"+e.getMessage());
			e.printStackTrace();
			return null;
		}
    	}catch(SQLException e){
    		e.printStackTrace();
    		return null;
    	}
    	return rs;
    }

    // 增添/删除/修改
    public int executeUpdate(String sql) {
        int ret = 0;
        try {
            ret = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
 // 返回结果集的插入方法
    public ResultSet executeQuery2(String sql) {
    	ResultSet ret = null;
        try {
            ret = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    } 
    
    public boolean executeUpdate2(String sql){
    	if(conn==null){
    		connectDB();
    	}
    	try {
			Statement stmt=conn.createStatement();//得到一个结果集，拿来遍历
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
    	
    }

}
