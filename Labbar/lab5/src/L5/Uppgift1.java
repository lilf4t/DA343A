package L5;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Uppgift1 {
    //  ToDo: Beräkna summan av heltalen i filen heltat.dat


     // Denna metoden fanns i föreläsningen för att läsa datatyper,

     // 1. Man måste koppla strömmen till ett objekt av typen DataInputStream
    public void Sum(String filename) throws IOException {
        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
         // Nu vill vi räkna summan av heltalen, dis.readInt läser int från filen
            int sum = 0;
            int nbr = dis.readInt();
        // Kolla genom hela med en loop, där index är mindre än nbr
            for(int i=0; i<nbr; i++) {
                sum += dis.readInt();
            }
            System.out.println(sum);
        }
    }
    public static void main(String[] args) throws IOException {
        Uppgift1 upp1 = new Uppgift1();
        upp1.Sum("files/heltal.dat");
    }
}
