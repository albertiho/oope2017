package oope2017ht.tiedot;
import oope2017ht.tiedot.Tieto;

/**
 * Tieto luokan perivä Tiedosto-luokka, hakemisstojen sisällöksi
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017
 * <p>
 * @author Albert Iho (iho.albert.k@student.uta.fi)
 *
 */

 
public class Tiedosto extends Tieto  {
   /*
    * Attribuutit
    *
    */
    
   // tiedoston koko
   private int koko;
    
   
   /*
    * Rakentajat
    *
    */
   
   /** kaksiparametrinen rakentaja
    * @param n StringBuilder-tyyppinen olio nimen asettamiseksi
    * @param k tiedoston int-tyyppinen koko
    */
   public Tiedosto(StringBuilder n, int k) { // nimi n, koko k
      super(n);// kutsutaan yliluokan rakentajaa
      koko(k); // kutsutaan asettavaa metodia
   }
   
   /** kopiorakentaja
    * @param t kopioitavaksi tarkoitettu tiedosto
    */
   public Tiedosto(Tiedosto t) {
      super(t);
      koko = t.koko();
   }
   
   
   /*
    * Aksessorit
    *
    */
    
   public int koko() {
      return koko;
   }
   
   /** koon asettava operaatio
    * @param k tiedoston koko
    */
   public void koko(int k) {
      if (k > 0)
         koko = k;
      else
         throw new IllegalArgumentException("Error!");
   }
   
   /*
    * muut metodit
    *
    */
    
   @Override
   public String toString() {
      return super.toString() + " " + koko;
   }
}