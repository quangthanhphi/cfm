/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;

/**
 *
 * @author phiquangthanh
 */
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

class ThemBan extends Frame implements ActionListener {

    Panel panel1 = new Panel();
    Panel panel2 = new Panel(new GridLayout(3, 2, 10, 10));
    Panel panel3 = new Panel();
    Panel panel4 = new Panel();
    Panel panel5 = new Panel();
    Label lbThemBan = new Label("DANH SÁCH BÀN", Label.CENTER);
    Label lbMaBan = new Label("Mã bàn:     ");
    Label lbTenBan = new Label("Tên bàn:   ");
    Label lbGhiChu = new Label("Ghi chú:   ");
    Label lbSpace = new Label("   ");
    TextField txtMaBan = new TextField(5);
    TextField txtTenBan = new TextField(20);
    TextField txtGhiChu = new TextField(20);
    Button buttThem = new Button(" Thêm ");
    Button buttMoi = new Button(" Mới ");
    Button buttNext = new Button("Tiếp>");
    Button buttPrev = new Button("<Trước");
    Button buttFirst = new Button("<<Đầu");
    Button butttLast = new Button("Cuối>>");
    Button buttThoat = new Button(" Thoát ");
    java.sql.Connection con;
    java.sql.Statement stmt;
    ResultSet rs;

    public ThemBan(String title) {
        super(title);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose(); // Đóng cửa sổ hiện tại
            }
        });
        lbThemBan.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(lbThemBan, BorderLayout.NORTH);
        lbMaBan.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        panel2.add(lbMaBan);
        txtMaBan.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        panel2.add(txtMaBan);
        lbTenBan.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        panel2.add(lbTenBan);
        txtTenBan.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        panel2.add(txtTenBan);
        lbGhiChu.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        panel2.add(lbGhiChu);
        txtGhiChu.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        panel2.add(txtGhiChu);
        panel5.add(panel2);
        panel4.add(lbSpace);
        panel5.add(panel4);
        add(panel5, BorderLayout.CENTER);
        buttThem.setFont(new Font("Arial", Font.PLAIN, 16));
        panel3.add(buttThem);
        buttMoi.setFont(new Font("Arial", Font.PLAIN, 16));
        panel3.add(buttMoi);
        buttFirst.setFont(new Font("Arial", Font.PLAIN, 16));
        panel3.add(buttFirst);
        buttPrev.setFont(new Font("Arial", Font.PLAIN, 16));
        panel3.add(buttPrev);
        buttNext.setFont(new Font("Arial", Font.PLAIN, 16));
        panel3.add(buttNext);
        butttLast.setFont(new Font("Arial", Font.PLAIN, 16));
        panel3.add(butttLast);
        buttThoat.setFont(new Font("Arial", Font.PLAIN, 16));
        panel3.add(buttThoat);
        add(panel3, BorderLayout.SOUTH);
        buttThem.addActionListener(this);
        buttMoi.addActionListener(this);
        buttFirst.addActionListener(this);
        buttPrev.addActionListener(this);
        buttNext.addActionListener(this);
        butttLast.addActionListener(this);
        buttThoat.addActionListener(this);
        setLocation(200, 50);
        pack();
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttThem) {
            Them();
        }
        if (e.getSource() == buttMoi) {
            Moi();
        }
        if (e.getSource() == buttFirst) {
            First();
        }
        if (e.getSource() == buttPrev) {
            Pre();
        }
        if (e.getSource() == buttNext) {
            Next();
        }
        if (e.getSource() == butttLast) {
            Last();
        }
        if (e.getSource() == buttThoat) {
            setVisible(false);
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

    public void Them() {
        try {
            connect();
            rs.moveToInsertRow();
            rs.updateString(1, txtMaBan.getText());
            rs.updateString(2, txtTenBan.getText());
            rs.updateString(3, txtGhiChu.getText());
            rs.insertRow();
            txtMaBan.setText("");
            txtTenBan.setText("");
            txtGhiChu.setText("");
        } catch (Exception e) {
            System.err.println("Error: " + e.toString());
        }
    }

    public void Moi() {
        txtMaBan.setText("");
        txtTenBan.setText("");
        txtGhiChu.setText("");
    }

    public void First() {
        try {
            connect();
            rs.first();
            txtMaBan.setText(rs.getString(1));
            txtTenBan.setText(rs.getString(2));
            txtGhiChu.setText(rs.getString(3));
        } catch (Exception e) {
            System.err.println("Error: " + e.toString());
        }
    }

    public void Pre() {
        try {
            rs.previous();
            txtMaBan.setText(rs.getString(1));
            txtTenBan.setText(rs.getString(2));
            txtGhiChu.setText(rs.getString(3));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void Next() {
        try {
            rs.next();
            txtMaBan.setText(rs.getString(1));
            txtTenBan.setText(rs.getString(2));
            txtGhiChu.setText(rs.getString(3));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void Last() {
        try {
            connect();
            rs.last();
            txtMaBan.setText(rs.getString(1));
            txtTenBan.setText(rs.getString(2));
            txtGhiChu.setText(rs.getString(3));
        } catch (Exception e) {
            System.err.println("Error: " + e.toString());
        }
    }
}
