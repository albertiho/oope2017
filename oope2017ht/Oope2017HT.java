package oope2017ht;

/**
 * Harjoitustyön ajoluokka
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017
 * <p>
 * @author Albert Iho (iho.albert.k@student.uta.fi)
 *
 */
 
public class Oope2017HT {
   public static void main(String[] args) {
      // tervehditään käyttäjää
      System.out.println("Welcome to SOS.");
      
      // ajetaan pääsilmukka
      Kayttoliittyma kayttis = new Kayttoliittyma();
      kayttis.suorita();
      
      // hyvästellään käyttäjä
      System.out.println("Shell terminated.");
   }
}