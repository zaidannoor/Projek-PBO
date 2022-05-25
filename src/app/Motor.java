/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class Motor extends Kendaraan {
    Connector connector = new Connector();
       
    public String[][] getMotor() {
        String data[][] = new String[1][3];
        int jmlData = 0;
        try{
            String query = "SELECT * FROM kendaraan WHERE jenis=motor";
            connector.statement = connector.koneksi.createStatement();
            ResultSet resultSet = connector.statement.executeQuery(query);
            while(resultSet.next()){ //konversi tabel ke string
                data[jmlData][0] = resultSet.getString("id"); 
                data[jmlData][1] = resultSet.getString("username"); 
                data[jmlData][2] = resultSet.getString("password");
                jmlData++; 
            }
            connector.statement.close();
           
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            
        }finally{
            return data;
        }
    }
    
    public int insertKendaraan(String nama,String merek,String plat,String harga){

        try {
            String query = "INSERT INTO kendaraan (nama,merek,plat,harga,jenis) "
                    + "VALUES ('"+nama+"','"+merek+"','"+plat+"','"+harga+"','motor')";
            
            connector.statement = connector.koneksi.createStatement();
            connector.statement.executeUpdate(query); // eksekusi query

            JOptionPane.showMessageDialog(null,"Insert data Berhasil !!");
            
            return 1;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    


    
}
