/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import javax.swing.*;
import java.sql.SQLException;
/**
 *
 * @author Lenovo
 */
public class Users {
    Connector connector = new Connector();
    SHA256 sha256 = new SHA256();
    
    public String[][] getPenyewa(){
        String data[][] = new String[50][4];
        int jmlData = 0;
        try{
            String query = "SELECT id,nik,username,alamat FROM users WHERE role='penyewa'";
            connector.statement = connector.koneksi.createStatement();
            ResultSet resultSet = connector.statement.executeQuery(query);
            while(resultSet.next()){ //konversi tabel ke string
                data[jmlData][0] = resultSet.getString("id"); 
                data[jmlData][1] = resultSet.getString("nik"); 
                data[jmlData][2] = resultSet.getString("username");
                data[jmlData][3] = resultSet.getString("alamat");
                jmlData++; 
            }
            connector.statement.close();
           
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            
        }finally{
            return data;
        }
    }
    
    public String[][] getPegawaiByUsername(String username){
        String data[][] = new String[1][3];
        int jmlData = 0;
        try{
            String query = "SELECT id,username,password FROM users WHERE username='"+username+"'"
                    + " AND role='pegawai' ";
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
    
    public String[][] getUserByNIK(String nik){
        String data[][] = new String[1][5];
        int jmlData = 0;
        try{
            String query = "SELECT * FROM users WHERE nik='"+nik+"'";
            connector.statement = connector.koneksi.createStatement();
            ResultSet resultSet = connector.statement.executeQuery(query);
            while(resultSet.next()){ //konversi tabel ke string
                data[jmlData][0] = resultSet.getString("id"); 
                data[jmlData][1] = resultSet.getString("nik"); 
                data[jmlData][2] = resultSet.getString("username");
                data[jmlData][3] = resultSet.getString("password"); 
                data[jmlData][4] = resultSet.getString("role");
                jmlData++; 
            }
            connector.statement.close();
           
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            
        }finally{
            return data;
        }
    }
    
    public boolean passwordMatch(String password,String passwordDB) throws NoSuchAlgorithmException{
        password = sha256.generate(password);
        if (password.equals(passwordDB)) {
            return true;
        }else{
            return false;
        }
   }
    
    public int insertPenyewa(String nik, String username,String alamat){
     
        try {
            String query = "INSERT INTO users (nik,username,password,alamat,role) "
                    + "VALUES ('"+nik+"','"+username+"','','"+alamat+"' ,'penyewa')";
            
            connector.statement = connector.koneksi.createStatement();
            connector.statement.executeUpdate(query); // eksekusi query

            JOptionPane.showMessageDialog(null,"Registrasi Berhasil !!");
            return 1;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return 0;
        }
    }
    
    public boolean checkID(String id){
        boolean found = false;
        try{
            String query = "SELECT id from users WHERE role='penyewa'";
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
    
    public boolean checkNIK(String nik){
        boolean found = false;
        try{
            String query = "SELECT nik from users";
            connector.statement = connector.koneksi.createStatement();
            ResultSet resultSet = connector.statement.executeQuery(query);
            while(resultSet.next()){ //konversi tabel ke string
                if(resultSet.getString("nik").equals(nik)){
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
    
    public int updatePenyewa(String id,String nik,String alamat){

        try {
            String query = "UPDATE users set nik='"+nik+"', alamat='"+alamat+"' WHERE id='"+id+"'";
                   
            
            connector.statement = connector.koneksi.createStatement();
            connector.statement.executeUpdate(query); // eksekusi query

            JOptionPane.showMessageDialog(null,"Update data Berhasil !!");
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
