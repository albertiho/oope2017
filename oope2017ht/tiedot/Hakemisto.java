package oope2017ht.tiedot;

/**
 * Hakemisto-luokka joka muodostaa harjoitusty�n rungon
 * Perii Tieto-luokan ja toteuttaa Komennettava-rajapinnan
 * <p>
 * Harjoitusty�, Olio-ohjelmoinnin perusteet, kev�t 2017
 * <p>
 * @author Albert Iho (iho.albert.k@student.uta.fi)
 *
 */
 
import apulaiset.*;
import fi.uta.csjola.oope.lista.*;
import oope2017ht.omalista.*;

public class Hakemisto extends Tieto implements Komennettava<Tieto> {
   /*
    * Attribuutit
    *
    */
    
   // hakemiston sis�lt�� kuvaava attribuutti (hakemiston tiedostot sek� muut hakemistot)
   private OmaLista hakemistonSisalto;
   
   // hakemiston ylihakemistoa kuvaava attribuutti
   private Hakemisto ylihakemisto;
   
   /*
    * Rakentajat
    *
    */
    
   /* parametriton rakentaja
    */
   public Hakemisto() {
      super();
      hakemistonSisalto = new OmaLista();
      ylihakemisto = null;
   }
    
   /** kaksiparametrinen rakentaja
    * @param n StringBuilder-tyyppinen olio nimiehdookkaaksi
    * @param y luotavan hakemiston ylihakemisto
    */
   public Hakemisto(StringBuilder n, Hakemisto y) {   // n = nimi, y = ylihakemisto
      super(n);
      hakemistonSisalto = new OmaLista();
      ylihakemisto(y);
   }
   
   /*
    * Aksessorit
    *
    */
    
   public OmaLista hakemistonSisalto() {
      return hakemistonSisalto;
   }
   
   public Hakemisto ylihakemisto() {
      return ylihakemisto;
   }
   
   /**hakemiston sis�ll�n asettava aksessori
    * @param l OmaLista-tyyppinen sis�ll�n ehdokas
    */
   public void hakemistonSisalto(OmaLista l) {
      hakemistonSisalto = l;
   }
   
   /** ylihakemiston asettava operaatio
    * @param h Hakemisto-tyyppinen ylihakemiston ehdokas
    */
   public void ylihakemisto(Hakemisto h) {
      ylihakemisto = h;
   }
   /*
    * Object-luokan metodien korvaaminen
    *
    */
   
   // toString metodin korvaaminen hakemistolle sopivaksi
   @Override
   public String toString() {
      return super.toString() + "/ " + hakemistonSisalto.koko();
   }
   
   /*
    * Komennettava-rajapinnan metodit
    *
    */
    
   /** aksessori joka antaa viitteen hakemiston sis�ll�n s�il�v��n listaan 
    * @return viite hakemisto-olion osaolioon
    */
   @Override
   public LinkitettyLista sisalto() {
      return hakemistonSisalto;
   }
   
   /** hakee hakemistosta tiedostoa tai alihakemistoa equals-metodin avulla
    * @param nimi haettavan tiedon nimi
    * @return viite l�ydettyyn tietoon, paluuarvo null jos haku ep�onnistuu
    */
   @Override
   public Tieto hae(String nimi) {
      if (nimi != null) {
         // luodaan apuolio ja annetaan se listan oman listan operaatioille
         Tieto haettava = new Tiedosto(new StringBuilder (nimi), 1337);
         Tieto apu = (Tieto)hakemistonSisalto.hae(haettava);
         return apu;
      }else 
         return null;

   }
   
   /** lis�� hakemistoon tidoston tai alihakemiston
    * @param lisattava viite lis�tt�v��n tietoon
    * @return true, jos lis�ys onnistuu, false ep�onnistuessa
    */
   @Override
   public boolean lisaa(Tieto lisattava) {
      return(hakemistonSisalto.lisaa(lisattava));
   }
   
   /** etsii ja poistaa hakemistosta tiedoston tai alihakemiston
    * @param nimi poistettavan tiedon nimi
    * @return viite poistettuun tietoon, paluuarvo null poiston ep�onnistuessa
    */
   @Override
   public Tieto poista(String nimi) {
      if (nimi != null) {
         Tieto poistettava = new Tiedosto(new StringBuilder(nimi), 1337);
         Tieto apu = (Tieto)hakemistonSisalto.poista(poistettava);
         return apu;
      }else 
         return null;
   }
}