/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.sql.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Lenovo
 */
public class ViewPenyewa {
    Connector connector = new Connector();
    
    JFrame window = new JFrame("Data Penyewa");
    JTable tabel;
    DefaultTableModel tableModel; //otomatis dibuat kalo buat JTable
    JScrollPane scrollPane;
    Object namaKolom[] = {"ID","NIK","Username","Alamat"};
    
    JLabel judul = new JLabel("Hapus Data Penyewa");
    JLabel lid = new JLabel("ID Penyewa  ");
        JTextField tfid = new JTextField();
    JLabel lnik = new JLabel("NIK  ");
        JTextField tfnik = new JTextField();
    JLabel lalamat = new JLabel("Alamat  ");
        JTextField tfalamat = new JTextField();
        
    JButton btnUbah = new JButton("Ubah Data");
        
    public ViewPenyewa(){
        Users penyewa = new Users();
        String data[][] = penyewa.getPenyewa();
        
        window.setLayout(null);
        window.setSize(800,600);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setVisible(true);
        
        // ADD COMPONENT
            window.add(judul);
            window.add(lid);
            window.add(tfid);
            window.add(lnik);
            window.add(tfnik);
            window.add(lalamat);
            window.add(tfalamat);
            window.add(btnUbah);
        
        // ATUR LETAK
            judul.setBounds(70, 310, 290, 20);
            lid.setBounds(40, 350, 120, 20);
            tfid.setBounds(170, 350, 100, 20);
            lnik.setBounds(40, 380, 120, 20);
            tfnik.setBounds(170, 380, 100, 20);
            lalamat.setBounds(40, 410, 120, 20);
            tfalamat.setBounds(170, 410, 100, 20);
            btnUbah.setBounds(120, 460, 100, 20);

        
        tabel = new JTable(data,namaKolom); //tabel merupakan variabel untuk tabelnya dengan isi tablemodel
        scrollPane = new JScrollPane(tabel);
        window.add(scrollPane);
        
        scrollPane.setBounds(20, 35, 730, 250);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        btnUbah.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent arg0) {
                    if(penyewa.checkID(getID())){
                        if (penyewa.checkNIK(getNIK())) {
                            penyewa.updatePenyewa(getID(),getNIK(),getAlamat());
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"NIK "+ getNIK()+ " sudah terdaftar di sistem");
                        }
                        
                    }else{
                        JOptionPane.showMessageDialog(null,"ID Penyewa "+ getID()+ " tidak ditemukan");
                    }
                    
                    
                }
        });
    }
    
    public String getID(){
        return tfid.getText();
    }
    public String getNIK(){
        return tfnik.getText();
    }
    public String getAlamat(){
        return tfalamat.getText();
    }
}
