package sample;

import javafx.scene.control.Alert;
import java.sql.*;

public class Dbms {
    static Connection conn = null;
    static String dbName = "student";
    static String url = "jdbc:mysql://localhost:3306/"+dbName;
    static String usrName = "root";
    static String pass = "aya2016";
    //Statement stmt;
    ResultSet rs;
    PreparedStatement ps;
    public Dbms() throws InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        try {
            conn = DriverManager.getConnection(url,usrName,pass);
            ps = conn.prepareStatement("SELECT * FROM student");
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void newRecord(String pName,String pTrack,String pEmail,String pPhone)
    {
        try {
            PreparedStatement pins = conn.prepareStatement("INSERT INTO student(name,track,email,phone) VALUES(?,?,?,?) ");
            pins.setString(1,pName);
            pins.setString(2,pTrack);
            pins.setString(3,pEmail);
            pins.setString(4,pPhone);
            int status = pins.executeUpdate();
            rs = ps.executeQuery();
            if(status != 0)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Record inserted succefully");
                alert.showAndWait();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setContentText("Error inserting the record");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateDb(String pId,String pName,String pTrack,String pEmail,String pPhone)
    {
        try {
        PreparedStatement pup = conn.prepareStatement("UPDATE student SET name = ? , track = ?, email = ? , phone = ? WHERE studentID = ?");
        pup.setString(1,pName);
        pup.setString(2,pTrack);
        pup.setString(3,pEmail);
        pup.setString(4,pPhone);
        pup.setInt(5,Integer.parseInt(pId));
        int status = pup.executeUpdate();
        rs = ps.executeQuery();
        if(status != 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Record Updated Succefully");
            alert.showAndWait();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("Error updating the record");
            alert.showAndWait();
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteDb(String pID)
    {
        try {
            PreparedStatement pdel = conn.prepareStatement("DELETE FROM student WHERE studentID=?");
            pdel.setInt(1,Integer.parseInt(pID));
            int status = pdel.executeUpdate();
            rs = ps.executeQuery();
            if(status != 0)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Record deleted succefully");
                alert.showAndWait();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setContentText("Error deleting the record");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public PersonalData getFirstRow()
    {
        PersonalData pd;
        try {
            if(rs.first())
            {
                pd = new PersonalData(rs.getInt("studentID"),rs.getString("name"), rs.getString("track"),rs.getString("email"),rs.getString("phone") );
                return pd;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public PersonalData getPrevRow(String id)
    {
        PersonalData pd;
        try {
            if(rs.previous()) {
                pd = new PersonalData(rs.getInt("studentID"),
                        rs.getString("name"), rs.getString("track"),
                        rs.getString("email"),rs.getString("phone") );
                return pd;
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public PersonalData getNextRow(String id)
    {
        PersonalData pd;
        try {
            if (rs.next()) {
                pd = new PersonalData(rs.getInt("studentID"),rs.getString("name"), rs.getString("track"),rs.getString("email"),rs.getString("phone") );
                return pd;
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public PersonalData getLastRow()
    {
        PersonalData pd;
        try {
            if(rs.last()) {
                pd = new PersonalData(rs.getInt("studentID"),rs.getString("name"), rs.getString("track"),rs.getString("email"),rs.getString("phone") );
                return pd;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void closeConnection()
    {
        try {
            conn.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
