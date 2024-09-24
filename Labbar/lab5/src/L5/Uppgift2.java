package L5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Uppgift2 {
    // ToDo: Skriv ett program som skriver ut samtliga medlemmars namn, antalet medlemmar och antalet kvinnliga medlemmar.
    // ToDo: Använd BufferedReader, InputStreamReader och FileInputStream. Teckenkodningen är "ISO-8859-1".

    public void PrintStats(String filename) throws IOException {
        //dessa läser text från fil på hårddisk.
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(filename), "ISO-8859-1"))) {
            int count = 0;
            int women = 0;
            //för o läsa namn
            String name = br.readLine();
            while(name != null) {
                //räknar ut antalet medlemmar i en while loop sålänge det finns ett namn
                count++;

                //räknar ut antalet kvinnor
                if(name.endsWith("K"))
                    women++;

                //denna har man med oavsett vad
                name = br.readLine();
                //skriver ut allas namn
                if(name != null)
                    //denna ska skriva ut alla men det går ej????????
                    System.out.println();
            }

            System.out.println("\nAntalet medlemmar: "+count+" därav " + women + " kvinnor.\n");
        }
    }
    public static void main(String[] args) throws IOException{
        Uppgift2 upp2 = new Uppgift2();
        upp2.PrintStats("files/medlemmar.txt");
    }
}
