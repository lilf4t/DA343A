package L6;

import javax.swing.*;
import java.awt.*;

public class Exercise3 extends Thread {

    public Exercise3(String[] messages, JLabel label, long pause) {
        //Din kod
    }

    public void run(){
        //Din kod
    }

    private class Write implements Runnable {
        //Den här klassen kan användas för att skapa objekt
        //som uppdaterar label-texten med hjälp av SwingUtilities.invokeLater

        @Override
        public void run() {
            //Din kod
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                JLabel lblText = new JLabel();
                String[] texter = {"Snart är det påsk",
                        "23 * 6735 = 154905",
                        "Den 28 oktober har Simone namnsdag"};
                lblText.setPreferredSize(new Dimension(400,40));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(lblText);
                frame.pack();
                frame.setVisible(true);
                
                Exercise3 ex3 = new Exercise3(texter, lblText, 3000);
                ex3.start();
            }
        });
    }

}
