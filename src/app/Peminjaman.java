/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class Peminjaman {
    Connector connector = new Connector();
    
    public int insertPeminjaman(String id_penyewa,String id_pegawai,String id_kendaraan,String lama_pinjam){
        try {
            String query = "INSERT INTO peminjaman (id_penyewa,id_pegawai,id_kendaraan,lama_pinjam) "
                    + "VALUES ('"+id_penyewa+"','"+id_pegawai+"','"+id_kendaraan+"','"+lama_pinjam+"')";
            
            connector.statement = connector.koneksi.createStatement();
            connector.statement.executeUpdate(query); // eksekusi query

            JOptionPane.showMessageDialog(null,"Insert data berhasil");
            return 1;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return 0;
        }
    }
}
