/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DetailOrderDTO;
import DTO.DetailTableDTO;
import DTO.OrderDTO;
import DTO.ShoppingCart;
import Utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.spire.pdf.*;
import java.awt.print.*;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.List;
import java.io.IOException;
/**
 *
 * @author handez
 */
public class OrderDao {
    public int insertOrder(String orderid, String number, int total,String tableid) throws SQLException{
        Connection cn = MyConnection.MakeConnection();
        int result = -1;
        if(cn!=null){
            String sql = "insert OrderTbl values(?,?,SYSDATETIME(),?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, orderid);
            pst.setString(2, number);
            pst.setInt(3, total);
            pst.setString(4, tableid);
            result = pst.executeUpdate();
            cn.close();
        }
        return result;
    }
    
    public ArrayList<OrderDTO> getOrder() throws SQLException{
        Connection cn = MyConnection.MakeConnection();
        ArrayList<OrderDTO> list = new ArrayList<>();
        if(cn!=null){
            String sql = "Select * from OrderTbl  order by date";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                list.add(new OrderDTO(rs.getString(2), rs.getString(1),rs.getString(3), rs.getInt(4)));
            }
            cn.close();
        }
        Collections.reverse(list);
        return list;
    }
    public int insertDetailOrder(String Orderid, String name , int quantity , int price , String product_id) throws SQLException{
        Connection cn = MyConnection.MakeConnection();
        int result = -1;
        if(cn!=null){
            String sql = "insert DetailOrderTbl values(?,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, Orderid);
            pst.setString(2, name);
            pst.setInt(3, quantity);
            pst.setInt(4, price);
            pst.setString(5, product_id);
            result = pst.executeUpdate();
            cn.close();
            
        }
        return result;
    }
    public ArrayList<DetailOrderDTO> getdetailOrder(String orderid) throws SQLException{
        Connection cn = MyConnection.MakeConnection();
        ArrayList<DetailOrderDTO>  dto = new ArrayList<>();
        if(cn!=null){
            String sql = " Select * from DetailOrderTbl where Order_id=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, orderid);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                dto.add( new DetailOrderDTO(rs.getString(1), rs.getString(2), rs.getInt(3),rs.getInt(4), rs.getString(5)));
            }
            cn.close();
        }
        return dto;
    }
    public ArrayList<OrderDTO> getOrderforUser(String number) throws SQLException{
        Connection cn = MyConnection.MakeConnection();
        ArrayList<OrderDTO> list = new ArrayList<>();
        if(cn!=null){
            String sql = "With ab AS (\n" +
"SELECT * , ROW_NUMBER() Over (ORDER BY  Date desc) AS RowNum FROM OrderTbl  where number= ?\n" +
")\n" +
"select * from ab\n" +
"WHERE RowNum >=1 \n" +
"AND RowNum <= 5";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, number);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                list.add(new OrderDTO(rs.getString(2), rs.getString(1),rs.getString(3), rs.getInt(4)));
            }
            cn.close();
        }
        Collections.reverse(list);
        return list;
    }
    public void order ( ArrayList<ShoppingCart> list,String tableid) throws SQLException, IOException{
                TableDao dao = new TableDao();
                Rectangle pageSize = new Rectangle(136, 841);
		Document document = new Document(pageSize,0,14,0,0);

		try {

			
			// Tạo đối tượng PdfWriter
			PdfWriter.getInstance(document, new FileOutputStream("D://Order.pdf"));
//C:\\FolderMill Data\\Hot Folders\\Hot Folders\\1\\Incoming\\viblo_asia.pdf"
			// Mở file để thực hiện ghi
			document.open();
                        
                        Paragraph someSectionText = new Paragraph(tableid,FontFactory.getFont(FontFactory.TIMES_BOLD, 16) );
                        someSectionText.setAlignment(Element.ALIGN_CENTER);
                        document.add(someSectionText);
                        
                        
                        
                        
                        
                        
                        
                       
                       
                       
                        int total =0;
                        for(int i =0;i<list.size();i++){
                             Paragraph ten = new Paragraph(list.get(i).getProduct().getName(),FontFactory.getFont(FontFactory.defaultEncoding, 14));
                             Paragraph sl = new Paragraph(""+list.get(i).getQuantity(),FontFactory.getFont(FontFactory.defaultEncoding, 14));
                             
                             document.add(ten);
                             document.add(sl);
                             document.addCreationDate();
                           
                        }
                       
                        
                        
			
			// Đóng File
			document.close();
			System.out.println("Write file succes!");
                        print("D://Order.pdf");
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
    }
    public void print(String input) {
    
        
        PdfDocument loDoc = new PdfDocument(input);
        PrinterJob loPrinterJob = PrinterJob.getPrinterJob();
        PageFormat loPageFormat  = loPrinterJob.defaultPage();
        Paper loPaper = loPageFormat.getPaper();
        //remove the default printing margins
        loPaper.setImageableArea(0,0,136,824);
        //set the number of copies
        loPrinterJob.setCopies(1);
        loPageFormat.setPaper(loPaper);
        loPrinterJob.setPrintable(loDoc,loPageFormat);

        try {
            loPrinterJob.print();
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    
}
    public void Bill(String orderid) throws IOException, SQLException{
        ArrayList<DetailOrderDTO> list = getdetailOrder(orderid);
        Rectangle pageSize = new Rectangle(136, 841);
		Document document = new Document(pageSize,0,14,0,0);

		try {

			
			// Tạo đối tượng PdfWriter
			PdfWriter.getInstance(document, new FileOutputStream("D:\\viblo_asia.pdf"));
//C:\\FolderMill Data\\Hot Folders\\Hot Folders\\1\\Incoming\\viblo_asia.pdf"
			// Mở file để thực hiện ghi
			document.open();
                        String title = "MOTEL&RESTAURENT HONG THAM\n";
                        String address = "\n45/32 Thuy Van , P2 TP Vung Tau\n";
                        String number = "\nHotline: 0918546075";
			// Thêm nội dung sử dụng add function
                        
                        
                        
                        Paragraph title1 = new Paragraph(title+"\n",FontFactory.getFont(FontFactory.defaultEncoding, 10));
                        title1.setAlignment(Element.ALIGN_CENTER);
                        title1.add("-------||-------\n");
                        title1.add(number);
                        title1.add(address);
                        title1.add("\n----------------------------");
                        document.add(title1);
//                      
                        Paragraph someSectionText = new Paragraph("HOA DON",FontFactory.getFont(FontFactory.TIMES_BOLD, 12) );
                        someSectionText.setAlignment(Element.ALIGN_CENTER);
                        document.add(someSectionText);
                        
                        
                        
                        
                        PdfPTable t = new PdfPTable(4);
                        t.setSpacingBefore(25);
                        t.setSpacingAfter(25);
                        t.setTotalWidth(118);
                        t.setLockedWidth(true);
                        float[] columnWidths = new float[]{40f, 10f, 10f, 10f};
                        t.setWidths(columnWidths);
                        PdfPCell c1 = new PdfPCell(new Phrase("Ten",FontFactory.getFont(FontFactory.defaultEncoding, 7)));
                        t.addCell(c1);
                        PdfPCell c2 = new PdfPCell(new Phrase("SL",FontFactory.getFont(FontFactory.defaultEncoding, 7)));
                        t.addCell(c2);
                        PdfPCell c3 = new PdfPCell(new Phrase("Gia",FontFactory.getFont(FontFactory.defaultEncoding, 7)));
                        t.addCell(c3);
                        PdfPCell c4 = new PdfPCell(new Phrase("",FontFactory.getFont(FontFactory.defaultEncoding, 7)));
                         t.addCell(c4);
                        int total =0;
                        for(int i =0;i<list.size();i++){
                             Paragraph ten = new Paragraph(list.get(i).getName(),FontFactory.getFont(FontFactory.defaultEncoding, 7));
                             Paragraph sl = new Paragraph(""+list.get(i).getQuantity(),FontFactory.getFont(FontFactory.defaultEncoding, 7));
                             Paragraph gia = new Paragraph(""+list.get(i).getPrice(),FontFactory.getFont(FontFactory.defaultEncoding, 7));
                             Paragraph tt = new Paragraph(""+(list.get(i).getQuantity()*list.get(i).getPrice()),FontFactory.getFont(FontFactory.defaultEncoding, 7));
                             t.addCell(ten);
                             t.addCell(sl);
                             t.addCell(gia);
                             t.addCell(tt);
                             total += list.get(i).getPrice()*list.get(i).getQuantity();
                        }
                        Paragraph total1 = new Paragraph("Total:"+total+".000VND",FontFactory.getFont(FontFactory.defaultEncoding, 7));
                        total1.setAlignment(Element.ALIGN_RIGHT);
                        document.add(t);
                        document.add(total1);
			Paragraph end = new Paragraph(""+"\n",FontFactory.getFont(FontFactory.defaultEncoding, 10));
                        end.setAlignment(Element.ALIGN_CENTER);
                        end.add("----------------------------");
                        end.add("\n \n Cam on Quy Khach ! \n\n\n");
                        document.add(end);
			// Đóng File
			document.close();
			System.out.println("Write file succes!");
                        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler D:\\viblo_asia.pdf");
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
                
    }
}
