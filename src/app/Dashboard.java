/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.security.NoSuchAlgorithmException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class Dashboard{
    Connector connector = new Connector();
    
    //DEKLARASI KOMPONEN  
    JFrame window = new JFrame("Dashboard");
    JLabel judul = new JLabel("Halaman Dashboard");
    JLabel greeting;
    JLabel linputdatakendaraan = new JLabel("Input data kendaraan");
    JLabel linputdatapenyewa = new JLabel("Input data Penyewa");
    JLabel lihatdata = new JLabel("Lihat data");
    JLabel lnamakendaraan = new JLabel("Nama Kendaraan  ");
        JTextField tfnamakendaraan = new JTextField();
    JLabel lmerek = new JLabel("Merek  ");
        JTextField tfmerek = new JTextField();
    JLabel lplat = new JLabel("No Plat  ");
        JTextField tfplat = new JTextField();
    JLabel lharga = new JLabel("Harga sewa sehari  ");
        JTextField tfharga = new JTextField();
    JLabel ljenis = new JLabel("Jenis Kendaraan   ");
    JRadioButton rbmotor = new JRadioButton("motor");
    JRadioButton rbmobil = new JRadioButton("mobil");   
     
    JLabel lnik = new JLabel("NIK  ");
        JTextField tfnik = new JTextField();
    JLabel lnamapenyewa = new JLabel("Nama Penyewa  ");
        JTextField tfnamapenyewa = new JTextField();
    JLabel lalamat = new JLabel("Alamat ");
        JTextField tfalamat = new JTextField();
        
        
    JButton btnKendaraan = new JButton("Data Kendaraan");
    JButton btnPeminjaman = new JButton("Data Rental");
    JButton btnPenyewa = new JButton("Data Penyewa");
    JButton btnSewa = new JButton("Sewa Kendaraan");
    JButton btnSubmitKendaraan = new JButton("Submit Data Kendaraan");
    JButton btnSubmitPenyewa = new JButton("Submit Data Penyewa");
    
    
    
    public Dashboard(String nama){
        
        greeting = new JLabel("Selamat datang, " + nama);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setSize(1000,500);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        
        // ADD Component
            window.add(judul);
            window.add(greeting);
            window.add(linputdatakendaraan);
            window.add(linputdatapenyewa);
            window.add(lihatdata);
            // kendaraan
            window.add(lnamakendaraan);
            window.add(lmerek);
            window.add(lharga);
            window.add(lplat);
            window.add(tfplat);
            window.add(tfnamakendaraan);
            window.add(tfmerek);
            window.add(tfharga);
            window.add(ljenis);
            ButtonGroup group = new ButtonGroup(); 
            group.add(rbmotor);
            group.add(rbmobil);
            window.add(rbmotor);
            window.add(rbmobil);
            
            // penyewa
            window.add(lnik);
            window.add(lnamapenyewa);
            window.add(lalamat);
            window.add(tfnik);
            window.add(tfnamapenyewa);
            window.add(tfalamat);
            // button
            window.add(btnKendaraan);
            window.add(btnPeminjaman);
            window.add(btnPenyewa);
            window.add(btnSewa);
            window.add(btnSubmitKendaraan);
            window.add(btnSubmitPenyewa);
            

            
        // Atur Letak
            judul.setBounds(470, 12, 120, 20);
            greeting.setBounds(50, 55, 160, 20);
            lihatdata.setBounds(110, 100, 160, 20);
            linputdatakendaraan.setBounds(440, 100, 160, 20);
            linputdatapenyewa.setBounds(745, 100, 160, 20);
            // bagian kendaraan
            lnamakendaraan.setBounds(340, 150, 120, 20);
            lmerek.setBounds(340, 190, 120, 20);
            lplat.setBounds(340, 230, 120, 20);
            lharga.setBounds(340, 270, 120, 20);
            ljenis.setBounds(340, 310, 120, 20);
            tfnamakendaraan.setBounds(465, 152, 125, 20);
            tfmerek.setBounds(465, 192, 125, 20);
            tfplat.setBounds(465, 232, 125, 20);
            tfharga.setBounds(465, 272, 125, 20);
            rbmotor.setBounds(465, 310, 80, 20);
            rbmobil.setBounds(545, 310, 80, 20);
            
            // bagian penyewa
            lnamapenyewa.setBounds(680, 150, 120, 20);
            lnik.setBounds(680, 190, 120, 20);
            lalamat.setBounds(680, 230, 120, 20);
            tfnamapenyewa.setBounds(790, 152, 120, 20);
            tfnik.setBounds(790, 192, 120, 20);
            tfalamat.setBounds(790, 232, 120, 20);
            
            btnKendaraan.setBounds(80, 160, 140, 20);
            btnPenyewa.setBounds(80,205,140,20);
            btnPeminjaman.setBounds(80,245,140,20);
            btnSubmitPenyewa.setBounds(750, 272, 180, 20);
            btnSubmitKendaraan.setBounds(410, 350, 180, 20);
            btnSewa.setBounds(40,380,200,50);
            
            
            btnSubmitKendaraan.addActionListener(new ActionListener() {
                String jenis;
                @Override
                public void actionPerformed(ActionEvent arg0) {
                   // String jenis;
                    if(rbmotor.isSelected()){
                        jenis = "motor";
                    }else if(rbmobil.isSelected()){
                        jenis = "mobil";
                    }
                   
                    if(jenis == "motor"){
                        Motor motor = new Motor();
                        motor.insertKendaraan(getNamaKendaraan(),getMerek(),getPlat(),getHarga());
                        
                    }else if(jenis == "mobil"){
                        Mobil mobil = new Mobil();
                        mobil.insertKendaraan(getNamaKendaraan(),getMerek(),getPlat(),getHarga());
                    }
                    
                }
            });
            
            btnSubmitPenyewa.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent arg0) {
                    Users penyewa = new Users();
                    String data[][] = penyewa.getUserByNIK(getNIK());
                    if(data[0][0] == null){
                        penyewa.insertPenyewa(getNIK(),getNamaPenyewa(),getAlamat());
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"NIK telah terdaftar dalam sistem");
                    }
                }
            });
            
            btnKendaraan.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent arg0) {
                    ViewKendaraan viewkendaraan = new ViewKendaraan();
                    
                }
            });
            
            btnSewa.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent arg0) {
                    Sewa sewa = new Sewa();
                }
            });
            
            btnPeminjaman.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent arg0) {
                    ViewPeminjaman viewpeminjaman = new ViewPeminjaman();
//                    
                }
            });
            
            btnPenyewa.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent arg0) {
                    ViewPenyewa viewpenyewa = new ViewPenyewa();
//                    
                }
            });
            
    }


    public String getNamaKendaraan(){
        return tfnamakendaraan.getText();
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
  
    public String getNamaPenyewa(){
        return tfnamapenyewa.getText();
    }
    public String getNIK(){
        return tfnik.getText();
    }
    public String getAlamat(){
        return tfalamat.getText();
    }
    
}
