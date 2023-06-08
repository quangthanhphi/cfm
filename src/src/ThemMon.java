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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThemMon extends Frame implements ActionListener {

    Panel panelCenter = new Panel();
    Panel panelSub = new Panel(new GridLayout(3, 2, 10, 10));
    Panel panelSouth = new Panel();
    Panel panelSpace = new Panel();
    Label lbSpace = new Label(" ");
    Label lbThemMon = new Label("THAY ĐỔI MÓN", Label.CENTER);
    Label lbTenBan = new Label("Tên Bàn:");
    Label lbKqua = new Label();
    Label lbTenDouong = new Label("Đồ uống:");
    Label lbSoluong = new Label("Số lượng thêm:");
    Choice chBanID = new Choice();
    Choice chTenBan = new Choice();
    Choice chDoUongID = new Choice();
    Choice chTenDoUong = new Choice();
    TextField txtSoLuong = new TextField(20);
    Button buttLuu = new Button("   Lưu   ");
    Button buttThoat = new Button(" Thoát ");
    java.sql.Connection con;
    java.sql.Statement stmt;
    ResultSet rs;

    public ThemMon(String title) {
        super(title);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose(); // Đóng cửa sổ hiện tại
            }
        });
        lbThemMon.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(lbThemMon, BorderLayout.NORTH);
        panelSub.add(lbTenBan);
        try {
            connect();
            rs = stmt.executeQuery("SELECT * FROM Ban");
            rs.next();
            while (!rs.isAfterLast()) {
                chBanID.addItem(rs.getString(1));
                chTenBan.addItem(rs.getString(2));
                rs.next();
            }
        } catch (SQLException se) {
            System.err.println("Error: " + se.getMessage());
        }
        panelSub.add(chTenBan);
        panelSub.add(lbTenDouong);
        try {
            connect();
            rs = stmt.executeQuery("SELECT * FROM Thuc_don");
            rs.next();
            while (!rs.isAfterLast()) {
                chDoUongID.addItem(rs.getString(1));
                chTenDoUong.addItem(rs.getString(2));
                rs.next();
            }
        } catch (SQLException se) {
            System.err.println("Error: " + se.getMessage());
        }
        panelSub.add(chTenDoUong);
        panelSub.add(lbSoluong);
        panelSub.add(txtSoLuong);
        panelCenter.add(panelSub);
        panelSpace.add(lbSpace);
        panelCenter.add(panelSpace);
        add(panelCenter, BorderLayout.CENTER);
        panelSouth.add(buttLuu);
        panelSouth.add(buttThoat);
        add(panelSouth, BorderLayout.SOUTH);
        buttLuu.addActionListener(this);
        buttThoat.addActionListener(this);
        setFont(new Font("Arial", Font.PLAIN, 13));
        setLocation(200, 50);
        pack();
        setVisible(true);
        setResizable(false);
    }
    //phuong thuc xu ly su kien

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttLuu) {
            Luu();
        }
        if (e.getSource() == buttThoat) {
            setVisible(false);
        }
    }
    //ham su thay doi tren du lieu ma nguoi dung nhap vao

    public void Luu() {
        try {
            connect();
            //rs chua cac ban ghi tren bang Goi_mon
            rs = stmt.executeQuery("SELECT * FROM Goi_mon");
            String Ban = chBanID.getItem(chTenBan.getSelectedIndex());
            String DoUong = chDoUongID.getItem(chTenDoUong.getSelectedIndex());
            rs.next();
            int i = 1;
            while (!((rs.getString(2).equals(Ban)) && (rs.getString(3).equals(DoUong)))) {
                rs.next();
                i++;
            }
            rs.absolute(i);
            rs.updateLong(4, (rs.getLong(4) + (Long.valueOf(txtSoLuong.getText()))));
            rs.updateRow();//cap nhat lai ban ghi co trong rs va bang Goi_mon
            txtSoLuong.setText("");
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
