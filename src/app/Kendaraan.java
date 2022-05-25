/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.sql.*;

/**
 *
 * @author Lenovo
 */
public class Kendaraan {
    Connector connector = new Connector();
    
    public String[][] getDataKendaraan() {
        String data[][] = new String[10][5];
        int jmlData = 0;
        try{
            String query = "SELECT * FROM kendaraan";
            connector.statement = connector.koneksi.createStatement();
            ResultSet resultSet = connector.statement.executeQuery(query);
            while(resultSet.next()){ //konversi tabel ke string
                data[jmlData][0] = resultSet.getString("nama"); 
                data[jmlData][1] = resultSet.getString("merek"); 
                data[jmlData][2] = resultSet.getString("plat");
                data[jmlData][3] = resultSet.getString("harga");
                data[jmlData][4] = resultSet.getString("jenis");
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

    
    
}
