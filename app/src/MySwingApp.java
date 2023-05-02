/**
 * @author: Christy Guo
 * @create_date: ${DATE} ${TIME}
 * @desc:
 * @modifier:
 */

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MySwingApp {
    private int STATE = 0;
    private int CURRENT_STATE = 5;  //Four states, 0: South to North 1: North to South 2: West to East 3: East
    private int LINE_1 = 110;
    private int LINE_2 = 460;

    private int SPEED = 10;     //speed
    private int LayerX = 500;   //position of the car
    private int LayerY = 100;   //position of the car

    private Frame frame = new Frame("Traffic Light");
    private final int TABALE_WIDTH = 600;   //Canvas width
    private final int TABALE_HEIGHT = 600;  //Canvas height
    private final int REC_WIDTH = 150;      //Light board width
    private final int REC_HEIGHT = 50;      //Light plate height
    private final int C_SIZE = 30;          //The diameter of the lamp

    Button start = new Button("Start");
    Button simulate = new Button("Simulate");
    Button stop = new Button("Stop");
    Button exit = new Button("Exit");

    private Timer timer;    //Time listener for lamp changes
    private Timer CAR_timer;    //Time listener for trolley movement

    private class MyCanvas extends Canvas {
        @Override
        public void paint(Graphics g){
            g.drawLine(150, 0, 150, 600);
            g.drawLine(300, 50, 300, 550);
            g.drawLine(450, 0, 450, 600);
            g.drawLine(0, 150, 600, 150);
            g.drawLine(50, 300, 550, 300);
            g.drawLine(0, 450, 600, 450);

            g.fillRect(150, 100, REC_WIDTH, REC_HEIGHT);
            g.fillRect(300, 450, REC_WIDTH, REC_HEIGHT);
            g.fillRect(100, 300, REC_HEIGHT, REC_WIDTH);
            g.fillRect(450, 150, REC_HEIGHT, REC_WIDTH);

            if (LayerY > 500 || LayerX < 0){
                LayerY = 0;
                LayerX = 500;
            }

            if (CURRENT_STATE == 0){
                g.fillOval(225, LayerX, 20, 20);
                g.setColor(Color.GREEN);

                g.fillOval(160, LINE_1, C_SIZE, C_SIZE); // light 1
                g.fillOval(210, LINE_1, C_SIZE, C_SIZE); // light 2
                g.fillOval(260, LINE_1, C_SIZE, C_SIZE); // light 3

                g.fillOval(LINE_2, 160, C_SIZE, C_SIZE); // light 4
                g.fillOval(405, LINE_2, C_SIZE, C_SIZE); // light 7
                g.fillOval(LINE_1, 405, C_SIZE, C_SIZE); // light 10

                g.setColor(Color.RED);

                g.fillOval(LINE_2, 210, C_SIZE, C_SIZE); // light 5
                g.fillOval(LINE_2, 260, C_SIZE, C_SIZE); // light 6

                g.fillOval(360, LINE_2, C_SIZE, C_SIZE); // light 8
                g.fillOval(310, LINE_2, C_SIZE, C_SIZE); // light 9


                g.fillOval(LINE_1, 360, C_SIZE, C_SIZE); // light 11
                g.fillOval(LINE_1, 310, C_SIZE, C_SIZE); // light 12

            }
            if (CURRENT_STATE == 1){
                g.fillOval(375, LayerY, 20, 20);//
                g.setColor(Color.GREEN);

                g.fillOval(310, LINE_2, C_SIZE, C_SIZE); // light 9
                g.fillOval(360, LINE_2, C_SIZE, C_SIZE); // light 8
                g.fillOval(405, LINE_2, C_SIZE, C_SIZE); // light 7

                g.fillOval(160, LINE_1, C_SIZE, C_SIZE); // light 1
                g.fillOval(LINE_2, 160, C_SIZE, C_SIZE); // light 4
                g.fillOval(LINE_1, 405, C_SIZE, C_SIZE); // light 10

                g.setColor(Color.RED);

                g.fillOval(210, LINE_1, C_SIZE, C_SIZE); // light 2
                g.fillOval(260, LINE_1, C_SIZE, C_SIZE); // light 3

                g.fillOval(LINE_2, 210, C_SIZE, C_SIZE); // light 5
                g.fillOval(LINE_2, 260, C_SIZE, C_SIZE); // light 6

                g.fillOval(LINE_1, 360, C_SIZE, C_SIZE); // light 11
                g.fillOval(LINE_1, 310, C_SIZE, C_SIZE); // light 12
            }
            if (CURRENT_STATE == 2){
                g.fillOval(LayerY, 225, 20, 20);
                g.setColor(Color.GREEN);

                g.fillOval(LINE_2, 160, C_SIZE, C_SIZE); // light 4
                g.fillOval(LINE_2, 210, C_SIZE, C_SIZE); // light 5
                g.fillOval(LINE_2, 260, C_SIZE, C_SIZE); // light 6

                g.fillOval(160, LINE_1, C_SIZE, C_SIZE); // light 1
                g.fillOval(405, LINE_2, C_SIZE, C_SIZE); // light 7
                g.fillOval(LINE_1, 405, C_SIZE, C_SIZE); // light 10

                g.setColor(Color.RED);

                g.fillOval(210, LINE_1, C_SIZE, C_SIZE); // light 2
                g.fillOval(260, LINE_1, C_SIZE, C_SIZE); // light 3

                g.fillOval(360, LINE_2, C_SIZE, C_SIZE); // light 8
                g.fillOval(310, LINE_2, C_SIZE, C_SIZE); // light 9

                g.fillOval(LINE_1, 360, C_SIZE, C_SIZE); // light 11
                g.fillOval(LINE_1, 310, C_SIZE, C_SIZE); // light 12
            }
            if (CURRENT_STATE == 3){
                g.fillOval(LayerX, 375, 20, 20);//小车,这里只设置一辆直行的小车
                g.setColor(Color.GREEN);

                g.fillOval(LINE_1, 310, C_SIZE, C_SIZE); // light 12
                g.fillOval(LINE_1, 360, C_SIZE, C_SIZE); // light 11
                g.fillOval(LINE_1, 405, C_SIZE, C_SIZE); // light 10

                g.fillOval(160, LINE_1, C_SIZE, C_SIZE); // light 1
                g.fillOval(LINE_2, 160, C_SIZE, C_SIZE); // light 4
                g.fillOval(405, LINE_2, C_SIZE, C_SIZE); // light 7

                g.setColor(Color.RED);

                g.fillOval(210, LINE_1, C_SIZE, C_SIZE); // light 2
                g.fillOval(260, LINE_1, C_SIZE, C_SIZE); // light 3

                g.fillOval(LINE_2, 210, C_SIZE, C_SIZE); // light 5
                g.fillOval(LINE_2, 260, C_SIZE, C_SIZE); // light 6

                g.fillOval(360, LINE_2, C_SIZE, C_SIZE); // light 8
                g.fillOval(310, LINE_2, C_SIZE, C_SIZE); // light 9
            }
        }
    }
    MyCanvas drawArea = new MyCanvas();
    public void init() {
        // Time listener of the lamp, which updates the status of the lamp every 1000ms
        ActionListener LIGHT_task = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                LayerY = 500;
                LayerX = 100;
                CURRENT_STATE = STATE % 4;
                drawArea.repaint();
                STATE += 1;
            }
        };
        //Time listener of the lamp, which updates the status of the lamp every 1000ms
        ActionListener CAR_task = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                LayerY += SPEED;
                LayerX -= SPEED;
                drawArea.repaint();
            }
        };
        //The Start key responds to the event
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CURRENT_STATE = 0;
                drawArea.repaint();
            }
        });
        //Simulate presses respond to events
        simulate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer = new Timer(1000, LIGHT_task);
                timer.start();
                CAR_timer = new Timer(20, CAR_task);
                CAR_timer.start();
                drawArea.repaint();
            }
        });

        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                CAR_timer.stop();
                drawArea.repaint();
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        Panel panel = new Panel();
        panel.add(start);
        panel.add(simulate);
        panel.add(stop);
        panel.add(exit);
        frame.add(panel, BorderLayout.SOUTH);
        drawArea.setPreferredSize(new Dimension(TABALE_WIDTH, TABALE_HEIGHT));
        frame.add(drawArea);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
    public static void main(String[] args) {
        new MySwingApp().init();
    }
}
