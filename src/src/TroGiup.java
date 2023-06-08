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

public class TroGiup extends Frame implements ActionListener {

    Label lbTroGiup = new Label("TRỢ GIÚP", Label.CENTER);
    TextArea txaTroGiup = new TextArea();
    Button buttThoat = new Button("Thoát");

    public TroGiup(String title) {
        super(title);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose(); // Đóng cửa sổ hiện tại
            }
        });
        lbTroGiup.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(lbTroGiup, BorderLayout.NORTH);
        txaTroGiup.setEditable(false);
        txaTroGiup.setText("\t\tCHƯƠNG TRÌNH QUẢN LÝ CAFE\n\t\tCoffee Manager V1.0\n"
                + "\nĐây là chương trình giúp cho việc quản lý quán cafe trở nên dễ dàng, chính xác hơn."
                + "\nChương trình này bao gồm các chức năng cơ bản như sau:"
                + "\n+ Nhập danh sách những món mà Khách muốn gọi."
                + "\n+ Sửa đổi món mà khách đã gọi trước đó. "
                + "\n+ Nếu khách có nhu cầu tách bàn (ví dụ như anh chị nào đó muốn dành riêng...)."
                + "\n+ Tính tiền một cách chính xác cho từng bàn."
                + "\nVà rất nhiều tính năng khác cho mọi người khám phá...");
        add(txaTroGiup, BorderLayout.CENTER);
        add(buttThoat, BorderLayout.SOUTH);
        buttThoat.addActionListener(this);
        setFont(new Font("Times New Roman", Font.PLAIN, 15));
        setBounds(120, 100, 600, 280);
        setResizable(false);
        //pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == buttThoat) {
            setVisible(false);
        }
    }

}
