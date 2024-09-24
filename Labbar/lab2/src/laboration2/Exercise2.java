package laboration2;
import java.util.*;

public class Exercise2 {
    private ArrayList<Person> list = new ArrayList<Person>();

    public Exercise2(String filename) {
        list = Exercise1.readPersons(filename);
    }

    public String toString() {
        return "Lagrade Person-objekt: " + list.toString();
    }

    public void printPersons() {
        for(Person p : list) {
            System.out.println("Personnummer: " + p.getId() + "," + " Namn: " +
                    p.getFirstName() + " " + p.getLastName());
        }
    }

    // returnera positionen (index) av Person-objektet med respektive id.
    // Om inget sådant objekt finns ska -1 returneras. I din lösning ska du loopa genom listan tills
    //du hittar ett objekt med korrekt id eller listan tar slut.
    public int position( String id ) {
        Person p;
        for( int i = 0; i < list.size(); i++ ) {
            p = list.get( i );
            if( id.equals( p.getId() ) )
                return i;
        }
        return -1;
    }

    //Metoden ska skriva namnet på den person vars id skickas med vid anropet. Om id ej
    //hittas ska ett meddelande liknande ”111111-2222 okänd” skrivas ut.
    public void printName( String id ) {
        int index = position( id );
        if( index >= 0 ) {
            Person p = list.get( index );
            System.out.println( p.getFirstName() + " " + p.getLastName() );
        } else {
            System.out.println( id + " okänd" );
        }
    }

    // Metoden ska returnera true om det finns en person med angivet förnamn. Finns det
    // inte någon person med det förnamnet ska false returneras.
    public boolean existsFirstName( String firstName ) {
        Iterator<Person> iter = list.iterator();
        Person p;
        while( iter.hasNext() ) {
            p = iter.next();
            if( firstName.equals( p.getFirstName() ) )
                return true;
        }
        return false;
    }
     //Metoden ska söka efter ett Person-objekt med angivet id. Om sådan finns ska
     //efternamnet ändras till angivet efternamnet som ges som argument vid anropet.
    public boolean changeLastName( String id, String lastName ) {
        int pos = position( id );
        if( pos >= 0 ) {
            Person p = list.get( pos );
            p.setLastName( lastName );
        }
        return ( pos >= 0 );
    }

    // Uppgift 3
    // Metoden ska returnera det sökta person-objektets position i list om det finns och annars -1

    // Först skapas ett Person-objekt med angivet personnummer (id).
    // Sedan anropas metoden indexOf i klassen ArrayList och det skapade Person- objektet är argument vid anropet.
    public int position2(String id) {
        Person p = new Person(id,"","");
        return list.indexOf(p);
    }

    public boolean equals(Object obj) {
        return (obj instanceof Person && ((Person)obj).getId().equals(id));
        }






    public static void main( String[] args ) {
        Exercise2 ex2 = new Exercise2( "files/personer.txt", p);
        System.out.println( ex2.toString() );
        ex2.printPersons();
        System.out.println( ex2.position( "840102-4567" ) );
        System.out.println( ex2.position( "111111-2222" ) );
        ex2.printName( "840102-4567" );
        ex2.printName( "111111-2222" );
        System.out.println( ex2.existsFirstName( "Edit" ) );
        System.out.println( ex2.existsFirstName( "Anna" ) );
        ex2.changeLastName( "660503-8901", "Trädrot" );
        System.out.println( ex2.toString() );

        // Uppgift 3a
        //System.out.println( ex2.position2("840102-4567") );

    }
}
