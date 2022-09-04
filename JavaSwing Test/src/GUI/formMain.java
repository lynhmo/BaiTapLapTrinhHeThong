package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

public class formMain {
    private JPanel panelMain;
    private JButton btnYES;

    public formMain() {
        btnYES.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(null,"Ban muon dong app?");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("frmMain");
        frame.setContentPane(new formMain().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
