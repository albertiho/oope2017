package oope2017ht;

/**
 * Harjoitusty�n ajoluokka
 * <p>
 * Harjoitusty�, Olio-ohjelmoinnin perusteet, kev�t 2017
 * <p>
 * @author Albert Iho (iho.albert.k@student.uta.fi)
 *
 */
 
public class Oope2017HT {
   public static void main(String[] args) {
      // tervehdit��n k�ytt�j��
      System.out.println("Welcome to SOS.");
      
      // ajetaan p��silmukka
      Kayttoliittyma kayttis = new Kayttoliittyma();
      kayttis.suorita();
      
      // hyv�stell��n k�ytt�j�
      System.out.println("Shell terminated.");
   }
}