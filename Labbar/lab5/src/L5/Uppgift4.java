package L5;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Uppgift4 {
    //ToDo: beräkna Person-objekten
    // genomsnittliga ålder. Beräkna dessutom kvinnornas genomsnittliga ålder
    // Lämpliga klasser att använda i lösningen är ObjectInputStream och FileInputStream.

    public void statistics(String filename) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new
                FileInputStream(filename))) {
            int n;
            int sum = 0;
            int women = 0;
            int sumWomen = 0;

            n = ois.readInt();

            //ny objekt av person
            Person[] p = new Person[n];
            for(int i=0; i<n; i++) {
                try {
                    //för o få ut ålder, av klassen Person, read object och lagra i p[i]
                    p[i] = (Person) ois.readObject();
                    sum += p[i].getAge();

                    if(p[i].getSex()=='K') {
                        sumWomen += p[i].getAge();
                        women++;
                    }
                }catch(ClassNotFoundException e) {
                    System.out.println(e);
                }
            }
            System.out.println("Genomsnittlig ålder är "+(double)sum/n);
            System.out.println("Kvinnornas genomsnittlig ålder är "+
                    (double)sumWomen/women);
        }
    }

    public static void main(String[] args) throws IOException {
        Uppgift4 upp4 = new Uppgift4();
        upp4.statistics("files/persons.dat");
    }
}