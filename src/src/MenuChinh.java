/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package src;

/**
 *
 * @author phiquangthanh
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuChinh {

    public static void main(String[] args) {
        createMenu();
    }

    private static void createMenu() {
        //tao frame cho chuong trinh
        final JFrame fr = new JFrame("Chương trình quản lý Cafe");
        fr.setBounds(0, 0, 800, 600);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setLayout(new BorderLayout());

        //tao Menu Bar
        JMenuBar menu = new JMenuBar();
        //tao cac Menu
        JMenu menuFile = new JMenu("File");
        JMenu menuUpdate = new JMenu("Cập Nhật");
        JMenu menuProcess = new JMenu("Xử Lý");
        JMenu menuHelp = new JMenu("Help");
        //tao Menu Item cho File
        JMenuItem trangThai = new JMenuItem("Trạng Thái Bàn");       
        trangThai.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new TrangThai("Trạng Thái Bàn");
            }
        });
       
        
        JMenuItem goiMon = new JMenuItem("Gọi Món");
        goiMon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new GoiMon("Gọi Món");
            }
        });
        
        JMenuItem tinhTien = new JMenuItem("Tính Tiền");
        tinhTien.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new TinhTien("Tính Tiền");
            }
        });
        
        JMenuItem thoat = new JMenuItem("Thoát");
        thoat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        menuFile.add(trangThai);
        menuFile.add(goiMon);
        menuFile.add(tinhTien);
        menuFile.addSeparator();
        menuFile.add(thoat);
        //tao Menu Item cho Update
        JMenuItem themBan = new JMenuItem("Danh Sách Bàn");
        themBan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new ThemBan("Danh Sách Bàn");
            }
        });
        JMenuItem themThucDon = new JMenuItem("Thực Đơn");
        themThucDon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new ThucDon("Thực Đơn");
            }
        });
        menuUpdate.add(themBan);
        menuUpdate.add(themThucDon);
        //tao Menu Item cho Process
        //tao Menu Item cho Help
        JMenuItem troGiup = new JMenuItem("Trợ Giúp");
        troGiup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new TroGiup("Trợ Giúp");
            }
        });
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new About("About");
            }
        });
        menuHelp.add(troGiup);
        menuHelp.add(about);
        //gan cac Menu vao MenuBar
        menu.add(menuFile);
        menu.add(menuUpdate);
        JMenuItem thayDoi = new JMenuItem("Thay Đổi Món");
        thayDoi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new ThemMon("Thay Đổi Món");
            }
        });
        JMenuItem ghepBan = new JMenuItem("Ghép Bàn");
        ghepBan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new GhepBan("Ghép Bàn");
            }
        });
        menuProcess.add(thayDoi);
        menuProcess.add(ghepBan);
        menu.add(menuProcess);
        menu.add(menuHelp);
        //gan MenuBar vao khung
        fr.setJMenuBar(menu);

        // Tạo một JPanel để chứa hình nền
        ImageIcon backgroundImage = new ImageIcon("images//coffee.png");
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        // Tạo label và đặt thuộc tính
        JLabel lb = new JLabel("CHƯƠNG TRÌNH QUẢN LÝ QUÁN CAFE", JLabel.CENTER);
        lb.setFont(new Font("Times Roman", Font.BOLD, 30));
        lb.setForeground(Color.BLACK); // Đặt màu chữ là màu trắng

        // Thêm label vào backgroundPanel
        backgroundPanel.add(lb, BorderLayout.NORTH);

        // Thay đổi phương thức setContentPane() của JFrame để đặt JPanel làm nền cho frame
        fr.setContentPane(backgroundPanel);
        
        fr.setFont(new Font("Arial", Font.PLAIN, 13));
        fr.setVisible(true);

        
        // Lắng nghe sự kiện thay đổi kích thước của JFrame
        fr.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Cập nhật kích thước của backgroundPanel khi kích thước của JFrame thay đổi
                backgroundPanel.setBounds(0, 0, fr.getWidth(), fr.getHeight());
//                backgroundLabel.setBounds(0, 0, backgroundPanel.getWidth(), backgroundPanel.getHeight());
            }
        });

        //xu ly su kien dong cua so ung dung
        fr.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
