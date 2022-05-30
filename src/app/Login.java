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
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Lenovo
 */
public class Login {
    Connector connector = new Connector();
    
    //DEKLARASI KOMPONEN
    JFrame window = new JFrame("Login");
    JLabel judul = new JLabel("Halaman Login");
    JLabel lusername = new JLabel("Username  ");
        JTextField tfusername = new JTextField();
    JLabel lpassword= new JLabel("password  ");
        JTextField tfpassword = new JTextField();
    
    JButton btnLogin = new JButton("Login");
    JButton btnClose = new JButton("Close");
    JButton btnGanti = new JButton("Ganti Password");
    
    public Login(){
        window.setLayout(null);
        window.setSize(350,230);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        
        // ADD Component
            window.add(judul);
            window.add(lusername);
            window.add(lpassword);
            window.add(tfusername);
            window.add(tfpassword);
            window.add(btnLogin);
            window.add(btnClose);
            //window.add(btnGanti);
            

            
        // Atur Letak
            judul.setBounds(110, 12, 120, 20);
            lusername.setBounds(5, 45, 120, 20);
            lpassword.setBounds(5, 70, 120, 20);

            tfusername.setBounds(85, 45, 125, 20);
            tfpassword.setBounds(85, 70, 125, 20);

            btnLogin.setBounds(65, 110, 80, 20);
            btnClose.setBounds(175, 110, 80, 20);
            btnGanti.setBounds(90,155,140,20);
        
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0); // close program
            }
        });
        
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Users user = new Users();
                String data[][] = new String[1][3];
                data = user.getPegawaiByUsername(getUsername()); // mengambil data dari database
               
                if(data[0][0] == null){ // mengecek ada atau tidak username tersebut di database
                                        // jika tidak ada maka tampilkan pesan kesalahan
                    JOptionPane.showMessageDialog(null,"Username anda tidak terdaftar dalam aplikasi kami");
                    JOptionPane.showMessageDialog(null,"Mohon cek kembali username anda");
                }
                else{ // jika ada maka kerjakan perintah berikut
                    String password = getPassword(); // mengambil inputan password user
                    String passwordDB = data[0][2]; // mengambil password yang didapat dari query getUserByUsername
                    try {
                        if(user.passwordMatch(password,passwordDB)){ // mengecek password inputan dengan password di database
                                                                    // lebih jelasnya bisa lihat method passwordMatch di model users
                            JOptionPane.showMessageDialog(null,"Login Berhasil");
                            Dashboard dashboard = new Dashboard(data[0][1]);
                            window.setVisible(false);
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Login gagal, password anda salah");
                            JOptionPane.showMessageDialog(null,"tolong masukkan pasword anda dengan beanr");;
                        }
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
        });
    }
    
    public String getUsername(){
        return tfusername.getText();
    }
    public String getPassword(){
        return tfpassword.getText();
    }
}
