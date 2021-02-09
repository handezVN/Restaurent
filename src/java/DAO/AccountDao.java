/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.AccountDTO;
import Utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author handez
 */
public class AccountDao {
    public AccountDTO checklogin(String number , String password) throws SQLException{
        Connection cn = MyConnection.MakeConnection();
        AccountDTO account = null;
        if(cn!=null){
            String sql = "Select * from UserTBL  where number = ? and password = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, number);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                account =new AccountDTO(rs.getString(3), rs.getString(1), rs.getBoolean(5));
            }
            cn.close();
        }
        return account;
    }
}
