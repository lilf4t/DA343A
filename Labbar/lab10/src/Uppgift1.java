
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Uppgift1 {

    public Uppgift1() {
        JFrame frame = new JFrame("IP-Checker");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new UI());
        frame.pack();
        frame.setVisible(true);
    }

    private class UI extends JPanel implements ActionListener {
        private JLabel lblLocalIp = new JLabel(" ");
        private JTextField tfHostname = new JTextField();
        private JLabel lblIp = new JLabel(" ");

        public UI() {
            JPanel pnlHostname = new JPanel(new BorderLayout());
            pnlHostname.add(new JLabel("Host name:"),BorderLayout.WEST);
            pnlHostname.add(tfHostname);


            //ToDo: Skapa ett objekt av klassen InetAddress (java.net)
            // Metoden getLocalHost() returnerar ett objekt som innehåller uppgifter om datorns adress (bl.a. datorns ip och namn)
            // Informationen ska skrivas i JLabel lblLocalIp ( lblLocalIp.setText(...) )
            // Använd klassen InetAddress i java.net (https://docs.oracle.com/javase/7/docs/api/java/net/InetAddress.html)
            // Tänk på att anropen till metoderna som behövs kan kasta en UnknownHostException!
 //---------------------------------------------------------------------------------------
            try {
                InetAddress myAddress = InetAddress.getLocalHost();
                lblLocalIp.setText("IP=" + myAddress.getHostAddress() +
                        "Namn=" + myAddress.getHostName());

            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }

//---------------------------------------------------------------------------------------
            setLayout(new GridLayout(3,1));
            setPreferredSize(new Dimension(300,100));
            add(lblLocalIp);
            add(pnlHostname);
            add(lblIp);

            tfHostname.addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            //TODO: Användaren kan mata in en webbadress (t.ex. mau.se eller google.se) och sedan ska webbadressens
            // ip-adress visas.
            // Webbadressen står i JTextField tfHostname ( tfHostname.getText() )
            // Använd klassen InetAddress i java.net (https://docs.oracle.com/javase/7/docs/api/java/net/InetAddress.html)
            // Tänk på att anropen till metoderna som behövs kan kasta en UnknownHostException!
           //  Metoden getByName(String host) returnerar ett objekt som innehåller
           //  uppgifter om en webbadress (bl.a. ip-adressen)
            // Informationen ska skrivas i JLabel lblIp ( lblIp.setText(...) )
            // lblIp.setText("Host ip: 1.1.1.1");

            try {
                InetAddress myAddress2 = InetAddress.getByName(tfHostname.getText());
                lblIp.setText("Host ip:" + myAddress2.getHostAddress());
            } catch (UnknownHostException ex) {
                lblIp.setText("Host ip: Unknown");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Uppgift1();
            }
        });
    }
}