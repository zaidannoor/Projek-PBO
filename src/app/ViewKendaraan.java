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
public class ViewKendaraan {
    Connector connector = new Connector();
    
    JFrame window = new JFrame("Data Kendaraan");
    JTable tabel;
    DefaultTableModel tableModel; //otomatis dibuat kalo buat JTable
    JScrollPane scrollPane;
    Object namaKolom[] = {"ID","Nama","Merek","Plat","Harga","Jenis","status"};
    
    JLabel judul = new JLabel("Ubah Data Kendaraan");
    JLabel lid = new JLabel("ID Kendaraan  ");
        JTextField tfid = new JTextField();
    JLabel lnama = new JLabel("Nama Kendaraan  ");
        JTextField tfnama = new JTextField();
    JLabel lmerek = new JLabel("Merek Kendaraan  ");
        JTextField tfmerek = new JTextField();
    JLabel lplat = new JLabel("Plat Kendaraan  ");
        JTextField tfplat = new JTextField();
     JLabel lharga = new JLabel("Harga Sewa  ");
        JTextField tfharga = new JTextField();
        
    JButton btnUbah = new JButton("Ubah Data");
    
    public ViewKendaraan(){
        Kendaraan kendaraan = new Kendaraan();
        String data[][] = kendaraan.getDataKendaraan();
        
        
        
        window.setLayout(null);
        window.setSize(800,600);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setVisible(true);
        
        // ADD COMPONENT
            window.add(judul);
            window.add(lid);
            window.add(tfid);
            window.add(lnama);
            window.add(tfnama);
            window.add(lmerek);
            window.add(tfmerek);
            window.add(lplat);
            window.add(tfplat);
            window.add(lharga);
            window.add(tfharga);
            window.add(btnUbah);
            
        // ATUR LETAK
            judul.setBounds(70, 310, 290, 20);
            lid.setBounds(40, 350, 120, 20);
            tfid.setBounds(170, 350, 100, 20);
            lnama.setBounds(40, 380, 120, 20);
            tfnama.setBounds(170, 380, 100, 20);
            lmerek.setBounds(40, 410, 120, 20);
            tfmerek.setBounds(170, 410, 100, 20);
            lplat.setBounds(40, 440, 120, 20);
            tfplat.setBounds(170, 440, 100, 20);
            lharga.setBounds(40, 470, 120, 20);
            tfharga.setBounds(170, 470, 100, 20);
            btnUbah.setBounds(80, 515, 120, 20);
        
        tabel = new JTable(data,namaKolom); //tabel merupakan variabel untuk tabelnya dengan isi tablemodel
        scrollPane = new JScrollPane(tabel);
        window.add(scrollPane);
        
        scrollPane.setBounds(20, 35, 730, 250);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        btnUbah.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent arg0) {
                    if(kendaraan.checkID(getID())){
                        kendaraan.updateKendaraan(getID(),getNama(),getMerek(),getPlat(),getHarga());
                    }else{
                        JOptionPane.showMessageDialog(null,"ID Kendaraan "+ getID()+ " tidak ditemukan");
                    }
                    
                    
                }
        });
    }
    
    public String getID(){
        return tfid.getText();
    }
    public String getNama(){
        return tfnama.getText();
    }
    public String getMerek(){
        return tfmerek.getText();
    }
    public String getPlat(){
        return tfplat.getText();
    }
    public String getHarga(){
        return tfharga.getText();
    }
}
