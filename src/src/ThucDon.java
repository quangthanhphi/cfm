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

class ThucDon extends Frame implements ActionListener {

    Panel panel1 = new Panel();
    Panel panel2 = new Panel(new GridLayout(3, 2, 10, 10));
    Panel panel3 = new Panel();
    Panel panel4 = new Panel();
    Panel panel5 = new Panel();
    Label lbThucDon = new Label("THỰC ĐƠN", Label.CENTER);
    Label lbMaDoUong = new Label("Mã đồ uống:   ");
    Label lbTenDoUong = new Label("Tên đồ uống: ");
    Label lbDonGia = new Label("Đơn giá:        ");
    Label lbSpace = new Label("   ");
    TextField txtMaDoUong = new TextField(10);
    TextField txtTenDoUong = new TextField(20);
    TextField txtDonGia = new TextField(20);
    Button buttMoi = new Button("  Mới  ");
    Button buttThem = new Button("  Thêm  ");
    Button buttSua = new Button("  Sửa  ");
    Button buttThoat = new Button("Thoát");
    Button buttNext = new Button("Tiếp>");
    Button buttPrev = new Button("<Trước");
    Button buttFirst = new Button("<<Đầu");
    Button butttLast = new Button("Cuối>>");
    java.sql.Connection con;
    java.sql.Statement stmt;
    ResultSet rs, rsTemp;

    public ThucDon(String title) {
        super(title);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose(); // Đóng cửa sổ hiện tại
            }
        });
        lbThucDon.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(lbThucDon, BorderLayout.NORTH);
        lbMaDoUong.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        panel2.add(lbMaDoUong);
        txtMaDoUong.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        panel2.add(txtMaDoUong);
        lbTenDoUong.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        panel2.add(lbTenDoUong);
        txtTenDoUong.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        panel2.add(txtTenDoUong);
        lbDonGia.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        panel2.add(lbDonGia);
        txtDonGia.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        panel2.add(txtDonGia);
        panel5.add(panel2);
        panel4.add(lbSpace);
        panel5.add(panel4);
        add(panel5, BorderLayout.CENTER);
        buttThem.setFont(new Font("Arial", Font.PLAIN, 16));
        panel3.add(buttMoi);
        buttMoi.setFont(new Font("Arial", Font.PLAIN, 16));
        panel3.add(buttThem);
        buttSua.setFont(new Font("Arial", Font.PLAIN, 16));
        panel3.add(buttSua);
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

        buttMoi.addActionListener(this);
        buttThem.addActionListener(this);
        buttSua.addActionListener(this);
        buttThoat.addActionListener(this);
        buttFirst.addActionListener(this);
        buttPrev.addActionListener(this);
        buttNext.addActionListener(this);
        butttLast.addActionListener(this);
        add(panel3, BorderLayout.SOUTH);
        setLocation(200, 50);
        pack();
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttMoi) {
            Moi();
        }
        if (e.getSource() == buttThem) {
            Them();
        }
        if (e.getSource() == buttSua) {
            Sua();
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
            rs.updateString(1, txtMaDoUong.getText());
            rs.updateString(2, txtTenDoUong.getText());
            rs.updateLong(3, Long.parseLong(txtDonGia.getText()));
            rs.insertRow();
            txtMaDoUong.setText("");
            txtTenDoUong.setText("");
            txtDonGia.setText("");
        } catch (Exception e) {
            System.err.println("Error: " + e.toString());
        }
    }
    //p thuc sua, chi cho phep sua 2 truong TenDoUong, va truong DonGia
    //khong duoc thay doi truong DoUongID nham tranh sai sot khi cap nhat DL

    public void Sua() {
        try {
            connect();
            rs.absolute(rs.getRow() + 1);
            rs.moveToInsertRow();
            rs.updateString(2, txtTenDoUong.getText());
            rs.updateLong(3, Long.parseLong(txtDonGia.getText()));
            rs.updateRow();
            txtMaDoUong.setText("");
            txtTenDoUong.setText("");
            txtDonGia.setText("");
        } catch (Exception e) {
            System.err.println("Error: " + e.toString());
        }
    }

    public void Moi() {
        txtMaDoUong.setText("");
        txtTenDoUong.setText("");
        txtDonGia.setText("");
    }

    public void First() {
        try {
            connect();
            rs.first();
            txtMaDoUong.setText(rs.getString(1));
            txtTenDoUong.setText(rs.getString(2));
            txtDonGia.setText(Long.toString(rs.getLong(3)));
        } catch (Exception e) {
            System.err.println("Error: " + e.toString());
        }
    }

    public void Pre() {
        try {
            rs.previous();
            txtMaDoUong.setText(rs.getString(1));
            txtTenDoUong.setText(rs.getString(2));
            txtDonGia.setText(Long.toString(rs.getLong(3)));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void Next() {
        try {
            rs.next();
            txtMaDoUong.setText(rs.getString(1));
            txtTenDoUong.setText(rs.getString(2));
            txtDonGia.setText(Long.toString(rs.getLong(3)));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void Last() {
        try {
            connect();
            rs.last();
            txtMaDoUong.setText(rs.getString(1));
            txtTenDoUong.setText(rs.getString(2));
            txtDonGia.setText(Long.toString(rs.getLong(3)));
        } catch (Exception e) {
            System.err.println("Error: " + e.toString());
        }
    }
}
