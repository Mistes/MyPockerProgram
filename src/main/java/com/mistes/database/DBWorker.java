package com.mistes.database;


import com.mysql.fabric.jdbc.FabricMySQLDriver;

import javax.print.DocFlavor;
import javax.swing.plaf.nimbus.State;
import java.sql.*;

/**
 * Created by Mistes on 24.06.2016.
 */
public class DBWorker {
    private static final String url = "jdbc:mysql://db4free.net:3306/mypockerbase";
    private static final String user = "pockerbase";
    private static final String password = "pockerbase";

     private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public DBWorker(){

           /*
        try{
        Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
           /*  connection = DriverManager.getConnection(url,user,password);
            if(!connection.isClosed()){
                System.out.println("Connecting with DataBase established");
            }
            connection.close();
            if(connection.isClosed()){
                System.out.println("Connecting with DataBase closed");
            }
        }catch (SQLException e){e.printStackTrace();}*/


                try{
                         connection = DriverManager.getConnection(url,user,password); /*Statement statement =connection.createStatement();*/

          //  statement.execute("INSERT INTO game(id, gameScore,gameName) VALUES (1,300,'First')");
          //  statement.executeUpdate("UPDATE pocker SET "count" = 500 WHERE id=1");
            System.out.println(connection.isClosed());
                 //   connection.close();
        }catch (SQLException e){e.printStackTrace();}

    }
}
