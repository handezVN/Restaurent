/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DetailTableDTO;
import DTO.TableDTO;
import Utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.xml.soap.Detail;

/**
 *
 * @author handez
 */
public class TableDao {
    public ArrayList<TableDTO> getTable() throws SQLException{
        Connection cn = MyConnection.MakeConnection();
        ArrayList<TableDTO> list = new ArrayList<>();
        if(cn!=null){
            String sql = "select * from TableTbl";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                list.add(new TableDTO(rs.getString(1), rs.getBoolean(2)));
            }
            cn.close();
        }
        return list;
    } 
    public int checkin(String id) throws SQLException{
        Connection cn = MyConnection.MakeConnection();
        int result = -1;
        if(cn!=null){
            String sql = "update TableTbl set Status=1 where Table_ID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, id);
            result=pst.executeUpdate();
            
            cn.close();
        }
        return result;
    }
    public int checkout(String id) throws SQLException{
        Connection cn = MyConnection.MakeConnection();
        int result = -1;
        if(cn!=null){
            String sql = "update TableTbl set Status=0 where Table_ID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, id);
            result=pst.executeUpdate();
            
            cn.close();
        }
        return result;
    }
    public int insert(String number , String productid , String tableid,int quantity,int price,String name) throws SQLException{
         Connection cn = MyConnection.MakeConnection();
        int result = -1;
        if(cn!=null){
            String sql = "insert DetailTableTbl values(?,?,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, tableid);
            pst.setString(2, productid);
            pst.setString(3, number);
            pst.setInt(4, quantity);
            pst.setInt(5, price);
            pst.setString(6, name);
            result=pst.executeUpdate();
            
            cn.close();
        }
        return result;

    }
    public ArrayList<DetailTableDTO> getTable(String table) throws SQLException{
        Connection cn = MyConnection.MakeConnection();
        ArrayList<DetailTableDTO> list = new ArrayList<>();
        if(cn!=null){
            String sql = "Select * from DetailTableTbl where Table_ID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, table);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                list.add(new DetailTableDTO(rs.getString(3), rs.getString(2), rs.getInt(4),rs.getInt(5),rs.getString(6)));
            }
            cn.close();
        }
        return list;
    }
    public int checkoutTalbe(String id) throws SQLException{
        Connection cn = MyConnection.MakeConnection();
        int result = -1;
        if(cn!=null){
            String sql = "delete DetailTableTbl where Table_ID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, id);
            result=pst.executeUpdate();
            
            cn.close();
        }
        return result;
    }
    public int updateQuantity(String product_id , String tableid,int quantity) throws SQLException{
        Connection cn = MyConnection.MakeConnection();
        int result = -1;
        if(cn!=null){
            String sql = "Update DetailTableTbl set quantity = ? where Table_ID= ? and Product_id=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, quantity);
            pst.setString(3, product_id);
            pst.setString(2, tableid);
            result=pst.executeUpdate();
            
            cn.close();
        }
        return result;
    }
    public int removeProduct(String tableid, String product_id) throws SQLException{
         Connection cn = MyConnection.MakeConnection();
        int result = -1;
        if(cn!=null){
            String sql = "Delete DetailTableTbl where Table_ID =? and Product_id=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, tableid);
            pst.setString(2, product_id);
            result=pst.executeUpdate();
            
            cn.close();
        }
        return result;
    }
    public int clearTable() throws SQLException{
         Connection cn = MyConnection.MakeConnection();
        int result = -1;
        if(cn!=null){
            String sql = 
            "Delete TableTbl ";
            PreparedStatement pst = cn.prepareStatement(sql);
           
          
            result=pst.executeUpdate();
            
            cn.close();
        }
        return result;
    }
    
    public int updateTable(String table) throws SQLException{
         Connection cn = MyConnection.MakeConnection();
        int result = -1;
        if(cn!=null){
            String sql = 
            "insert TableTbl values(?,0)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, table);
          
            result=pst.executeUpdate();
            
            cn.close();
        }
        return result;
    }
}