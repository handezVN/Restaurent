/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.OrderDTO;
import Utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author handez
 */
public class HistoryDao {
    public ArrayList<OrderDTO> getOrderbyMonthandYear(int month) throws SQLException{
        Connection cn = MyConnection.MakeConnection();
        ArrayList<OrderDTO> list = new ArrayList<>();
        if(cn!=null){
            String sql = "Select * from OrderTbl where date> (SELECT DATEADD(month, ?, '2021/01/1') AS DateAdd) and date <( SELECT DATEADD(month, ?, '2021/01/1') AS DateAdd) order by date";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, month);
            pst.setInt(2, (month+1));
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                list.add(new OrderDTO(rs.getString(2), rs.getString(1),rs.getString(3), rs.getInt(4)));
            }
            cn.close();
        }
        Collections.reverse(list);
        return list;
    }
}
