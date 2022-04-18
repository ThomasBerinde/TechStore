package presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class asdf {

    private JPanel panel1;
    private JButton button1;
    private JTable table1;
    private JScrollBar scrollBar1;

    public static void main(String[] args) {

        asdf asdf = new asdf();

        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        frame.add(asdf.panel1);
    }

    public asdf() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
