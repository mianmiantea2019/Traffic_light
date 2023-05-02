/**
 * @author: Christy Guo
 * @create_date: ${DATE} ${TIME}
 * @desc:
 * @modifier:
 */

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MySwingApp {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Traffic Light");

        frame.setSize(400, 300);

        JButton button = new JButton("Click me!");

        frame.getContentPane().add(button);
        button.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Hello, World!");
        });

        frame.setVisible(true);
    }
}