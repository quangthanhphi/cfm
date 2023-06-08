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

public class About extends Frame implements ActionListener {

    Label lbAbout = new Label("ABOUT", Label.CENTER);
    Label lbChuongTrinh = new Label("Coffee Manager V1.0", Label.CENTER);
    Label lb1 = new Label("Chương trình được thiết kế bởi:", Label.CENTER);
    Label lb2 = new Label("GVHD: Mai Xuân Hùng", Label.CENTER);
    Label lb3 = new Label("\nSVTH: Phan Nhật Quý", Label.CENTER);
    Label lb4 = new Label("           Lê Thị Minh Nguyệt", Label.CENTER);
    Label lb5 = new Label("             Ngô Anh Tuấn", Label.CENTER);
    Label lb6 = new Label("             Phi Quang Thành", Label.CENTER);

    Button buttThoat = new Button("  Thoát  ");

    public About(String title) {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose(); // Đóng cửa sổ hiện tại
            }
        });
        Panel panelCenter = new Panel(new GridLayout(8, 1));
        lbAbout.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(lbAbout, BorderLayout.NORTH);
        lbChuongTrinh.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        panelCenter.add(lbChuongTrinh);
        lb1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        panelCenter.add(lb1);
        lb2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        panelCenter.add(lb2);
        lb3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        panelCenter.add(lb3);
        lb4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        panelCenter.add(lb4);
        lb5.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        panelCenter.add(lb5);
        lb6.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        panelCenter.add(lb6);
        add(panelCenter, BorderLayout.CENTER);
        buttThoat.addActionListener(this);
        add(buttThoat, BorderLayout.SOUTH);
        setBounds(200, 100, 400, 250);
        setVisible(true);
        setResizable(false);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == buttThoat) {
            setVisible(false);
        }
    }
}
