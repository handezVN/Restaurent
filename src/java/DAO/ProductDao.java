/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ProductDTO;
import Utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author handez
 */
public class ProductDao {
    public ArrayList<ProductDTO> getProduct() throws SQLException{
        ArrayList<ProductDTO> list = new ArrayList<>();
        Connection cn = MyConnection.MakeConnection();
        if(cn!=null){
            String sql = "Select * from ProductTbl";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                list.add(new ProductDTO(rs.getString(1),rs.getString(3),rs.getInt(2)));
            }
            cn.close();
        }
        return list;
    }
    public ProductDTO getProductbyId(String id ) throws SQLException{
        ProductDTO pro = null;
        Connection cn = MyConnection.MakeConnection();
        if(cn!=null){
            String sql = "Select * from ProductTbl where Product_id=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
               pro = new ProductDTO(rs.getString(1),rs.getString(3),rs.getInt(2));
            }
            cn.close();
        }
        return pro;
    }
    public int UpdateProduct(String id , String name , String price) throws SQLException{
        int result = -1;
        Connection cn = MyConnection.MakeConnection();
        if(cn!=null){
            String sql = " Update ProductTbl set Name = ? , Price = ? where Product_id=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setInt(2, Integer.parseInt(price));
            pst.setString(3, id);
            result = pst.executeUpdate();
            cn.close();
        }
        return result;
    }
    public int AddProduct(String id , String name , String price) throws SQLException{
        int result = -1;
        Connection cn = MyConnection.MakeConnection();
        if(cn!=null){
            String sql = " Insert ProductTbl values(?,?,?) ";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setInt(2, Integer.parseInt(price));
            pst.setString(3, id);
            result = pst.executeUpdate();
            cn.close();
        }
        return result;
    }
    public int RemoveProduct(String id ) throws SQLException{
        int result = -1;
        Connection cn = MyConnection.MakeConnection();
        if(cn!=null){
            String sql = " Delete ProductTbl where  Product_id=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, id);
           
            result = pst.executeUpdate();
            cn.close();
        }
        return result;
    }
}
