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
public class ViewPeminjaman {
    Connector connector = new Connector();
    
    JFrame window = new JFrame("Data Penyewaan");
    JTable tabel;
    DefaultTableModel tableModel; //otomatis dibuat kalo buat JTable
    JScrollPane scrollPane;
    Object namaKolom[] = {"id","penyewa","Pegawai","kendaraan","tanggal pinjam","lama pinjam","harga","status"}; //membuat kolom dgn array tipe object;
    
    JLabel judul = new JLabel("Ubah Status atau Hapus Data Peminjaman");
    JLabel lid = new JLabel("ID Peminjaman  ");
        JTextField tfid = new JTextField();
    JButton btnUbah = new JButton("Ubah Status");
    JButton btnHapus = new JButton("Hapus Data");
        
    public ViewPeminjaman(){
        Peminjaman peminjaman = new Peminjaman();
        Kendaraan kendaraan = new Kendaraan();
        String data[][] = peminjaman.getDataPeminjaman();
        window.setLayout(null);
        window.setSize(800,600);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setVisible(true);
        
        // ADD COMPONENT
            window.add(judul);
            window.add(lid);
            window.add(tfid);
            window.add(btnUbah);
            window.add(btnHapus);
        
        // ATUR LETAK
            judul.setBounds(70, 370, 290, 20);
            lid.setBounds(40, 410, 120, 20);
            tfid.setBounds(170, 410, 90, 20);
            btnUbah.setBounds(50, 460, 120, 20);
            btnHapus.setBounds(190, 460, 120, 20);

            tabel = new JTable(data,namaKolom); //tabel merupakan variabel untuk tabelnya dengan isi tablemodel
            scrollPane = new JScrollPane(tabel);
            window.add(scrollPane);

            scrollPane.setBounds(20, 35, 730, 300);
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            
            
            btnUbah.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent arg0) {
                    Peminjaman peminjaman = new Peminjaman();
                    
                    if(peminjaman.checkID(getID())){
                        peminjaman.changeStatus(getID()); // ubah status jadi selesai
                        String id_kendaraan[][] = peminjaman.getIDKendaraan(getID()); // ambil id kendaraan
                        kendaraan.changeStatusToReady(id_kendaraan[0][0]);
                    }else{
                        JOptionPane.showMessageDialog(null,"ID peminjaman "+ getID()+ " tidak ditemukan");
                    }
                    
                }
            });
            
            btnHapus.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent arg0) {
                    Peminjaman peminjaman = new Peminjaman();
                    if(peminjaman.checkID(getID())){
                        peminjaman.deleteData(getID());
                    }else{
                        JOptionPane.showMessageDialog(null,"ID peminjaman "+ getID()+ " tidak ditemukan");
                    }
                    
                }
            });
    }
    
    public String getID(){
        return tfid.getText();
    }
}
