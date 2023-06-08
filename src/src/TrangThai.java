/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;

/**
 *
 * @author phiquangthanh
 */
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class TrangThai extends Frame implements ActionListener {

    int n = 0;//dung de duyet cac ban
    Panel panelSouth = new Panel();
    Label lbChinh = new Label("Trạng Thái Bàn", Label.CENTER);
    TextArea txaChinh = new TextArea();
    Button buttXem = new Button("   Xem   ");
    Button buttThoat = new Button("  Thoát  ");
    Choice chMaBan = new Choice();
    Choice chTenBan = new Choice();
    java.sql.Connection con;
    java.sql.Statement stmt;
    ResultSet rs;

    public TrangThai(String title) {
        super(title);
        lbChinh.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(lbChinh, BorderLayout.NORTH);
        txaChinh.setEditable(false);
        txaChinh.setText("\tTên Bàn\t\tTrạng thái\n\n");
        add(txaChinh, BorderLayout.CENTER);
        panelSouth.add(buttXem);
        panelSouth.add(buttThoat);
        add(panelSouth, BorderLayout.SOUTH);
        buttXem.addActionListener(this);
        buttThoat.addActionListener(this);
        setFont(new Font("Arial", Font.PLAIN, 14));
        setLocation(200, 50);
        setSize(400, 400);
        setResizable(false);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose(); // Đóng cửa sổ hiện tại
            }
        });
        try {
            connect();
            rs = stmt.executeQuery("SELECT * FROM Ban");
            rs.next();
            while (!rs.isAfterLast()) {
                chMaBan.addItem(rs.getString(1));
                chTenBan.addItem(rs.getString(2));
                n++;
                rs.next();
            }
        } catch (Exception e) {
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttXem) {
            Xem();
        }
        if (e.getSource() == buttThoat) {
            setVisible(false);
        }

    }

    public void Xem() {
        try {
            connect();
            rs = stmt.executeQuery("SELECT BanID FROM Goi_mon");
            for (int i = 0; i < n; i++) {
                String s = chMaBan.getItem(i);
                rs.first();
                boolean flag = false;
                while (!rs.isAfterLast()) {
                    if (rs.getString(1).equals(s)) {
                        flag = true;
                        break;
                    }
                    rs.next();
                }
                if (flag == true) {
                    txaChinh.append("\t" + chTenBan.getItem(i) + "\t\t\tDa su dung");
                } else {
                    txaChinh.append("\t" + chTenBan.getItem(i) + "\t\t\tChua su dung");
                }
                txaChinh.append("\n");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.toString());
        }
    }

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // Load trình điều khiển MySQL
        } catch (ClassNotFoundException ex) {
            System.out.print("Error: " + ex.getMessage());
        }
        try {
            String url = "jdbc:mysql://localhost:3306/database_name";
            String username = "root";
            String password = "";

            con = DriverManager.getConnection(url, username, password);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException se) {
            System.err.println("Error: " + se.getMessage());
        }
    }

}
