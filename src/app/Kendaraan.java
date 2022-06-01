/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class Kendaraan {
    Connector connector = new Connector();
    
    public String[][] getDataKendaraan() {
        String data[][] = new String[50][7];
        int jmlData = 0;
        try{
            String query = "SELECT * FROM kendaraan";
            connector.statement = connector.koneksi.createStatement();
            ResultSet resultSet = connector.statement.executeQuery(query);
            while(resultSet.next()){ //konversi tabel ke string
                data[jmlData][0] = resultSet.getString("id");
                data[jmlData][1] = resultSet.getString("nama"); 
                data[jmlData][2] = resultSet.getString("merek"); 
                data[jmlData][3] = resultSet.getString("plat");
                data[jmlData][4] = resultSet.getString("harga");
                data[jmlData][5] = resultSet.getString("jenis");
                data[jmlData][6] = resultSet.getString("status");
                jmlData++; 
            }
            connector.statement.close();
           
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            
        }finally{
            return data;
        }
    }
    
    public String[][] getKendaraanByPlat(String plat) {
        String data[][] = new String[1][6];
        int jmlData = 0;
        try{
            String query = "SELECT * FROM kendaraan WHERE plat='"+plat+"'";
            connector.statement = connector.koneksi.createStatement();
            ResultSet resultSet = connector.statement.executeQuery(query);
            while(resultSet.next()){ //konversi tabel ke string
                data[jmlData][0] = resultSet.getString("id");
                data[jmlData][1] = resultSet.getString("nama"); 
                data[jmlData][2] = resultSet.getString("merek"); 
                data[jmlData][3] = resultSet.getString("plat");
                data[jmlData][4] = resultSet.getString("harga");
                data[jmlData][5] = resultSet.getString("status"); 
                jmlData++; 
            }
            connector.statement.close();
           
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("salah");
            
        }finally{
            return data;
        }
    }
    
    public boolean checkID(String id){
        boolean found = false;
        try{
            String query = "SELECT id from kendaraan";
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
    
    public int updateKendaraan(String id,String nama,String merek,String plat,String harga){

        try {
            String query = "UPDATE kendaraan set nama='"+nama+"', "
                    + "merek='"+merek+"', plat='"+plat+"', harga='"+harga+"' WHERE id='"+id+"'";
            
            connector.statement = connector.koneksi.createStatement();
            connector.statement.executeUpdate(query); // eksekusi query

            JOptionPane.showMessageDialog(null,"Update data Berhasil !!");
            return 1;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return 0;
        }
    }
    
    public int changeStatusToNotReady(String id){
        try {
            String query = "UPDATE kendaraan SET status='not ready' WHERE id='"+id+"'";
            
            connector.statement = connector.koneksi.createStatement();
            connector.statement.executeUpdate(query); // eksekusi query

            //JOptionPane.showMessageDialog(null,"Status berhasil diubah menjadi selesai");
            return 1;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return 0;
        }
    }
    
    public int changeStatusToReady(String id){
        try {
            String query = "UPDATE kendaraan SET status='ready' WHERE id='"+id+"'";
            
            connector.statement = connector.koneksi.createStatement();
            connector.statement.executeUpdate(query); // eksekusi query

            //JOptionPane.showMessageDialog(null,"Status berhasil diubah menjadi selesai");
            return 1;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return 0;
        }
    }
    
    

    
    
}
