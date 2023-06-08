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

public class GoiMon extends Frame implements ActionListener {

    Panel panelCenter = new Panel();
    Panel panelSub = new Panel(new GridLayout(4, 2, 10, 10));
    Panel panelSouth = new Panel();
    Panel panelSpace = new Panel();
    Label lbSpace = new Label(" ");
    Label lbGoiMon = new Label("GỌI MÓN", Label.CENTER);
    Label lbMaGoi = new Label("Lượt gọi:");
    Label lbBan = new Label("Bàn:");
    Label lbDoUong = new Label("Đồ uống:");
    Label lbSoLuong = new Label("Số lượng:");
    TextField txtMaGoi = new TextField("Vd: 23 là Bàn 2,Lượt gọi 3");
    Choice chBan = new Choice();
    Choice chTenBan = new Choice();
    Choice chDoUong = new Choice();
    Choice chTenDoUong = new Choice();
    TextField txtSoLuong = new TextField();
    Button buttLuu = new Button("   Lưu   ");
    Button buttThoat = new Button(" Thoát ");
    java.sql.Connection con;
    java.sql.Statement stmt;
    ResultSet rs;

    public GoiMon(String title) {
        super(title);
        lbGoiMon.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(lbGoiMon, BorderLayout.NORTH);
        panelSub.add(lbMaGoi);
        panelSub.add(txtMaGoi);
        panelSub.add(lbBan);
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
                chBan.addItem(rs.getString(1));
                chTenBan.addItem(rs.getString(2));
                rs.next();
            }
        } catch (Exception e) {
        }
        try {
            connect();
            rs = stmt.executeQuery("SELECT * FROM Thuc_don");
            rs.next();
            while (!rs.isAfterLast()) {
                chDoUong.addItem(rs.getString(1));
                chTenDoUong.addItem(rs.getString(2));
                rs.next();
            }
        } catch (Exception e) {
        }

        panelSub.add(chTenBan);
        panelSub.add(lbDoUong);
        panelSub.add(chTenDoUong);
        panelSub.add(lbSoLuong);
        panelSub.add(txtSoLuong);
        panelCenter.add(panelSub);
        panelSpace.add(lbSpace);
        panelCenter.add(panelSpace);
        add(panelCenter, BorderLayout.CENTER);
        panelSouth.add(buttLuu);
        add(panelSouth, BorderLayout.SOUTH);
        panelSouth.add(buttThoat);
        add(panelSouth, BorderLayout.SOUTH);
        buttLuu.addActionListener(this);
        buttThoat.addActionListener(this);
        setFont(new Font("Arial", Font.PLAIN, 14));
        setLocation(180, 50);
        setSize(500, 250);
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttLuu) {
            Luu();
        }
        if (e.getSource() == buttThoat) {
            setVisible(false);
        }
    }

    public void Luu() {
        try {
            connect();
            rs = stmt.executeQuery("SELECT * FROM Goi_mon");
            rs.moveToInsertRow();
            rs.updateLong(1, Long.parseLong(txtMaGoi.getText()));
            int n = chTenBan.getSelectedIndex();
            rs.updateString(2, chBan.getItem(n));
            int m = chTenDoUong.getSelectedIndex();
            rs.updateString(3, chDoUong.getItem(m));
            rs.updateLong(4, Long.parseLong(txtSoLuong.getText()));
            rs.insertRow();
            txtMaGoi.setText("");
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
