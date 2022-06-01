/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.awt.Dimension;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class Peminjaman {
    Connector connector = new Connector();
     
    public int insertPeminjaman(String id_penyewa,String id_pegawai,String id_kendaraan,String lama_pinjam,int harga){
        try {
            String query = "INSERT INTO peminjaman (id_penyewa,id_pegawai,id_kendaraan,lama_pinjam,harga) "
                    + "VALUES ('"+id_penyewa+"','"+id_pegawai+"','"+id_kendaraan+"','"+lama_pinjam+"','"+harga+"')";
            
            connector.statement = connector.koneksi.createStatement();
            connector.statement.executeUpdate(query); // eksekusi query

            JOptionPane.showMessageDialog(null,"Insert data berhasil");
            return 1;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return 0;
        }
    }
    
    public String[][] getDataPeminjaman(){
        String data[][] = new String[50][8];
        int jmlData = 0;
        try {
            String query = "SELECT peminjaman.id,\n" +
                "	penyewa.username as penyewa,\n" +
                "	pegawai.username as pegawai,\n" +
                "	kendaraan.nama as kendaraan,\n" +
                "	peminjaman.tanggal_pinjam,\n" +
                "	peminjaman.lama_pinjam,\n" +
                "	peminjaman.harga,\n" +
                "	peminjaman.status\n" +
                "	FROM peminjaman\n" +
                "	inner join users pegawai on pegawai.id = peminjaman.id_pegawai\n" +
                "	inner join users penyewa on penyewa.id=peminjaman.id_penyewa\n" +
                "	inner join kendaraan on kendaraan.id=peminjaman.id_kendaraan;";
            connector.statement = connector.koneksi.createStatement();
            ResultSet resultSet = connector.statement.executeQuery(query);
            
            while(resultSet.next()){ //konversi tabel ke string
                data[jmlData][0] = resultSet.getString("id");
                data[jmlData][1] = resultSet.getString("penyewa"); 
                data[jmlData][2] = resultSet.getString("pegawai"); 
                data[jmlData][3] = resultSet.getString("kendaraan");
                data[jmlData][4] = resultSet.getString("tanggal_pinjam");
                data[jmlData][5] = resultSet.getString("lama_pinjam");
                data[jmlData][6] = resultSet.getString("harga");
                data[jmlData][7] = resultSet.getString("status");
                jmlData++; 
            }
              
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            return data;
        }
    }
    
    public String[][] getIDKendaraan(String id){
        String data[][] = new String[1][1];
        int jmlData = 0;
        try {
            String query = "SELECT id_kendaraan from peminjaman WHERE id='"+id+"'";
            connector.statement = connector.koneksi.createStatement();
            ResultSet resultSet = connector.statement.executeQuery(query);
            
            while(resultSet.next()){ //konversi tabel ke string
                data[jmlData][0] = resultSet.getString("id_kendaraan");
                
                jmlData++; 
            }
              
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            return data;
        }
    }
    
    
    
    public boolean checkID(String id){
        boolean found = false;
        try{
            String query = "SELECT id from peminjaman";
            connector.statement = connector.koneksi.createStatement();
            ResultSet resultSet = connector.statement.executeQuery(query);
            while(resultSet.next()){ //konversi tabel ke string
                if(resultSet.getString("id").equals(id)){
                    found = true;
                    break;
                }     
            }
            return found;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return found;
        }   
    }
    
    public int changeStatus(String id){
        try {
            String query = "UPDATE peminjaman SET status='selesai' WHERE id='"+id+"'";
            
            connector.statement = connector.koneksi.createStatement();
            connector.statement.executeUpdate(query); // eksekusi query

            JOptionPane.showMessageDialog(null,"Status berhasil diubah menjadi selesai");
            return 1;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return 0;
        }
    }
    
    
    
    public int deleteData(String id){
        try {
            String query = "DELETE FROM peminjaman WHERE id='"+id+"'";
            
            connector.statement = connector.koneksi.createStatement();
            connector.statement.executeUpdate(query); // eksekusi query

            JOptionPane.showMessageDialog(null,"Data berhasil dihapus");
            return 1;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return 0;
        }
    }
}
