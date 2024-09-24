package laboration1;

public interface DynamicString {
    /**
     * Antal tecken i strÃ¤ngen
     * @return antal tecken i strÃ¤ngen
     *
     * return length alltså
     */
    public int length();

    /**
     * Returnerar tecknet i angiven position i strÃ¤ngen
     * @param index positionen fÃ¶r tecknet som ska returneras
     * @return tecknet i positionen
     */
    public char charAt(int index);

    /**
     * Returnerar teckensekvensen som ett String-objket
     * @return Teckensekvensen som ett String-objekt
     */
    public String toString();

    /**
     * LÃ¤gger till ett tecken sist i strÃ¤ngen
     * @param chr Tecknet som ska lÃ¤ggas till
     */
    public DynamicString append(char chr);

    /**
     * LÃ¤gger till tecknen i ett DString-objekt sist i strÃ¤ngen
     * @param str StrÃ¤ngen som ska lÃ¤ggas till
     */
    public DynamicString append(DString str);

    /**
     * Tar bort tecken ur strÃ¤ngen. Det fÃ¶rsta tecknet som tas bort Ã¤r i positionen start och
     * teckenen fram till positionen (end-1) tas bort
     * @param start Positionen dÃ¤r borttagningen startas
     * @param end Tecken tas bort fram till positionen end. Tecknet i positionen end Ã¤r kvar i strÃ¤ngen.
     */
    public DynamicString delete(int start, int end);

    /**
     * Tar bort tecknet i angiven position
     * @param index Positionen fÃ¶r tecknet som ska tas bort
     */
    public DynamicString delete(int index);

    /**
     * Returnerar ett String-objekt som bestÃ¥r av tecknen frÃ¥n positionen start till positionen (end-1)
     * @param start Positionen start ger det fÃ¶rsta tecknet i returstrÃ¤ngen
     * @param end Positionen (end-1) ger det sista tecknet i returstrÃ¤ngen
     * @return Ett String-objekt som innehÃ¥ller tecknen frÃ¥n positionen start till positionen (end-1)
     */
    public String substring(int start, int end);

    /**
     * Returnerar ett String-objekt som bestÃ¥r av tecknen frÃ¥n positionen start
     * @param start Positionen start ger det fÃ¶rsta tecknet i returstrÃ¤ngen
     * @return ett String-objekt som innehÃ¥ller tecknen fr.o.m. positionen start
     */
    public String substring(int start);

    /**
     * Returnerar positionen fÃ¶r fÃ¶rsta fÃ¶rekomsten av tecknet chr. Om tecknet chr ej finns i strÃ¤ngen
     * returneras -1.
     * @param chr tecknet som metoden sÃ¶ker efter
     * @return positionen fÃ¶r fÃ¶rsta fÃ¶rekomsten av tecknet i strÃ¤ngen. Om tecknet ej finns i strÃ¤ngen
     * sÃ¥ returneras -1.
     */
    public int indexOf(char chr);
}
