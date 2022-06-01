/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 *
 * @author Lenovo
 */
public class Sewa {
    
    //deklarasi komponen
    JFrame window = new JFrame("Input data penyewaan");
    JLabel judul = new JLabel("Input Data Penyewaan");
    JLabel lnik = new JLabel("NIK  ");
        JTextField tfnik = new JTextField();
    JLabel lplat = new JLabel("Plat  ");
        JTextField tfplat = new JTextField();
    JLabel lpinjam = new JLabel("Lama peminjaman  ");
        JTextField tfpinjam = new JTextField();
    JLabel lpegawai = new JLabel("id Pegawai  ");
        JTextField tfpegawai = new JTextField();
    JButton btnSubmit = new JButton("Submit");
    
    public Sewa(){
        window.setLayout(null);
        window.setSize(350,300);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        
        // ADD Component
            window.add(judul);
            window.add(lnik);
            window.add(lplat);
            window.add(lpinjam);
            window.add(lpegawai);
            
            window.add(tfnik);
            window.add(tfplat);
            window.add(tfpinjam);
            window.add(tfpegawai);
            
            window.add(btnSubmit);
            
         // Atur Letak
            judul.setBounds(100, 12, 150, 20);
            lnik.setBounds(30, 60, 120, 20);
            lplat.setBounds(30, 90, 120, 20);
            lpinjam.setBounds(30, 120, 120, 20);
            lpegawai.setBounds(30, 150, 120, 20);
            
            tfnik.setBounds(155, 60, 120, 20);
            tfplat.setBounds(155, 90, 120, 20);
            tfpinjam.setBounds(155, 120, 120, 20);
            tfpegawai.setBounds(155, 150, 120, 20);
            
            btnSubmit.setBounds(100, 200, 120, 20);
            
            btnSubmit.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent arg0) {
                    Kendaraan kendaraan = new Kendaraan();
                    Users penyewa = new Users();
                    String datapenyewa[][] = penyewa.getUserByNIK(getNIK());
                    String datakendaraan[][] = kendaraan.getKendaraanByPlat(getPlat());
                    if((datakendaraan[0][0] == null) || (datapenyewa[0][0] == null)){
                        JOptionPane.showMessageDialog(null,"NIK tidak terdaftar dalam sistem "
                                + "atau kendaraan dengan plat tersebut tidak ada");
                        
                    }
                    else{
                        if("ready".equals(datakendaraan[0][5])){
                            String id_penyewa = datapenyewa[0][0];
                            String id_kendaraan = datakendaraan[0][0];
                            String id_pegawai = getPegawai();
                            String lama_pinjam = getPinjam();
                            int hargasehari = Integer.parseInt(datakendaraan[0][4]);
                            int total_harga = hargasehari * Integer.parseInt(lama_pinjam);
                           
                            Peminjaman peminjaman = new Peminjaman();
                            peminjaman.insertPeminjaman(id_penyewa, id_pegawai, id_kendaraan, lama_pinjam,total_harga);
                            kendaraan.changeStatusToNotReady(id_kendaraan);
                        }else{
                            JOptionPane.showMessageDialog(null,"Kendaraan yang diminta sedang tidak siap");
                            
                        }
                        
                        
                    }
                }
            });
    }
    
    public String getPlat(){
        return tfplat.getText();
    }
    public String getNIK(){
        return tfnik.getText();
    }
    public String getPinjam(){
        return tfpinjam.getText();
    }
    public String getPegawai(){
        return tfpegawai.getText();
    }
}
