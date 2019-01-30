package oope2017ht.tiedot;

/**
 * Harjoitusty�t� varten luotu abstrakti Tieto-luokka
 * <p>
 * Harjoitusty�, Olio-ohjelmoinnin perusteet, kev�t 2017
 * <p>
 * @author Albert Iho (iho.albert.k@student.uta.fi)
 *
 */
 
public abstract class Tieto implements Comparable<Tieto> {

   /*
    * Attribuutit
    *
    */
   
   // tiedon nimi 
   private StringBuilder nimi;
   
   
   /*
    * Rakentajat
    *
    */
 
   public Tieto() {
      nimi = new StringBuilder("/");
   }
   
   /** yksiparametrinen rakentaja, jossa alustetaan nimi (n)
    * @param n StringBuilder-tyyppinen olio nimeksi
    */
   public Tieto(StringBuilder n) {
      try {
         nimi(n);
      }
      catch (Exception e) {
         throw new IllegalArgumentException("Error!");
      }
   }
   
   /** kopiorakentaja, jossa tiedon nimeksi kopioidaan parametrina saadun tiedon nimi
    * @param t kopioitavan tiedon nimi
    */
   public Tieto(Tieto t) {
      // tarkistetaan ett� viitteeseen liittyy tieto-olio
      if (t instanceof Tieto) {
         // luodaan uusi olio samoilla arvoilla
         StringBuilder kopionimi = new StringBuilder(t.nimi());
         
         // asetetaan viite kopio-olioon
         nimi(kopionimi);
      }
   }
   
   
   /*
    * Aksessorit
    *
    */
   
   public StringBuilder nimi() {
      return nimi;
   }
   /** nimen asettava operaatio
    * @param n StringBuilder-tyyppinen olio jota testataan nimeksi
    */
   public void nimi(StringBuilder n) throws IllegalArgumentException {
      try {
         if (n != null){
            int pistelaskuri = 0;
            // k�yd��n nimiehdokas l�pi
            for( int i = 0; i < n.length(); i++ ) {
               if(Character.isLetter(n.charAt(i))){
               }
               else if(Character.isDigit(n.charAt(i))) {
               }
               else if (n.charAt(i) == '_') {
               }
               else if (n.charAt(i) == '.')  // pisteiden kohalla kasvatetaan laskuria
                  pistelaskuri++;
               else
                  throw new IllegalArgumentException("Error!");
            }
            if (pistelaskuri < 2) {
               nimi = n;
            }
            else
               throw new IllegalArgumentException("Error!");
         }else
            throw new IllegalArgumentException("Error!");
      }
      catch (Exception e) {
         throw new IllegalArgumentException("Error!");
      }
   }
   
   
   /*
    * Object-luokan metodien korvaaminen
    *
    */
    
   // korvataan Object-luokan toString metodien
   @Override
   public String toString() {
      return nimi.toString();
   }
   
   /* korvataan Object-luokan equals-metodi, k�sitell��n poikkeus metodissa
    */
   @Override
   public boolean equals(Object obj) {
      try {
         // asetetaan olioon Tieto-luokan viite jotta voidaan kutsua aksessoreita
         Tieto t = (Tieto)obj;
         StringBuilder apu = t.nimi(); // erotetaan oliosta k�sitelt�v�ksi vain nimi
         
         // m��ritell��n oliot String-muotoiseen esitykseen
         String vertailunimi = nimi.toString();
         String parametrinimi = apu.toString();
                  
         // oliot ovat samat jos nimet ovat merkilleen 
         return vertailunimi.equals(parametrinimi);
      }
      catch (Exception e) {
         return false;
      }
   }
   
   
   /*
    * Comparable-rajapinnan metodin toteutus
    *
    */
   @Override
   public int compareTo(Tieto o) {
      // haetaan Tieto-olion nimi
      StringBuilder apu = o.nimi();
      
      // String-tyyppisiksi vertailua varten
      String nykyinen = nimi.toString();
      String parametri = apu.toString();
      
      // suoritetaan vertailu
      return nykyinen.compareTo(parametri);
   }
}