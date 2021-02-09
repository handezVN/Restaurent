/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author handez
 */
public class MyConnection implements Serializable{
    public static Connection MakeConnection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Restaurent";
            Connection con = DriverManager.getConnection(url,"sa","hoiconcac");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
