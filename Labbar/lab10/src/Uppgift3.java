
import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;


// https://webshare.mah.se/an4263/test.txt


public class Uppgift3 {

    public static ImageIcon createImageIcon(String path) throws MalformedURLException {

        //TODO: Metoden ska skapa ett URL-objekt med metod-parametern path
        // Den hjälp av det objektet ska ett ImageIcon-objekt skapas som sedan returneras av metoden.
        // Tänk på alla exceptions som kan kastas!

        URL url = new URL(path);
        if(url != null) {
            return new ImageIcon(url);
        } else {
            System.out.println("Couldn't find file" + path);
            return null;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Image");
        frame.setSize(150, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = null;
        try {
            label = new JLabel( createImageIcon("https://webshare.mah.se/ak4314/gubbe.jpg") );
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.add(label, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
