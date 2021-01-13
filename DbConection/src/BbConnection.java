import java.sql.*;

public class BbConnection {
    static Connection conn = null;
    static  String dbName = "student";
    static String url = "jdbc:mysql://localhost:3306/"+dbName;
    static String usrName = "root";
    static String pass = "aya2016";

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        try {
            conn = DriverManager.getConnection(url,usrName,pass);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO `student` VALUES(2,'Sondos Medhat','cloud') ");
            int status = ps.executeUpdate();
            if(status != 0)
                System.out.println("Connected");
            else
                System.out.println("Error!");
            //conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
