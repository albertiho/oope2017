package oope2017ht.omalista;

/**
 * LinkitettyLista-luokan periv� OmaLista luokka
 * Toimii hakemistojen selk�rankana ja toteuttaa Ooperoiva rajapinnan
 * <p>
 * Harjoitusty�, Olio-ohjelmoinnin perusteet, kev�t 2017
 * <p>
 * @author Albert Iho (iho.albert.k@student.uta.fi)
 *
 */
 
import fi.uta.csjola.oope.lista.*;
import apulaiset.*;

public class OmaLista extends LinkitettyLista implements Ooperoiva {
   /*
    * Ooperoiva-rajapinnan operaatiot
    *
    */
   
   /** etsii listalta vastaavaa alkiota ja palauttaa viitteen alkioon
    * alkioiden monistumista ei tarvitse k�sitell� sill� kyseisess� ymp�rist�ss�
    * samanlaisten alkioiden luominen mahdotonta.
    * @param haettava listalta etsitt�v� tieto
    * @return viite l�ydettyyn alkioon, paluuarvo null jos haku ep�onnistuu
    */
   @Override
   public Object hae(Object haettava) {
      // k�yd��n lista l�pi ja etsit��n haettavaa
      for ( int i = 0; i < koko(); i++ ) {
         if (alkio(i).equals(haettava))
            // haettavan l�ytyess� palautetaan l�ydetty alkio
            return alkio(i);
      }
      return null;
   }
   
   /** lis�� alkion listalle oikeaan paikkaan.
    * oikea paikka = aakkosj�rjestyksen mukaan.
    * @param uusi viite lis�tt��v��n olioon
    * @return true, jos lis�ys onnistuu, false ep�onnistuessa
    */
   @SuppressWarnings("unchecked")
   @Override
   public boolean lisaa(Object uusi) {
      // jos uusi ei ole null, eik� sit� l�ydy listalta
      if (uusi != null && hae(uusi) == null) {
   
         try { // yritet��n lis�t� alkio listaan
            // asetetaan vertailtaviin olioihin comparable-rajapinnan viite
            Comparable uudempi = (Comparable)uusi;
            if (koko < 1) {  // jos lista on tyhj�, lis�t��n alkio alkuun
               lisaaAlkuun(uusi);
               return true;
               
            // kun listalla on muita alkioita, k�yd��n lista l�pi
            }else { 
               for ( int i = 0; i <= koko; i++ ) {
                  // jos koko lista on k�yty l�pi l�yt�m�tt� suurempaa alkiota
                  if (i == koko) {
                     lisaaLoppuun(uusi);  // lis�t��n suurin alkio loppuun
                     return true;   // paluuarvona totuus  
                     
                  // jos listalla oleva arvo on suurempi kuin lis�tt�v� arvo                  
                  }else if (uudempi.compareTo(alkio(i)) < 0) {
                     lisaa(i, uusi);   // sijoitetaan alkio listalle oikeaan paikkaan
                     return true;   // annetaan tosi paluuarvona onnistumisesta
                  }
               }
            }
         }
         catch (Exception e) {   // lis��misen ep�onnistuessa annetaan paluuarvona false
            return false;
         }
      }
      return false;
   }
   
   /** poistaa listalta oliota equals-mieless� vastaavan alkion,
    * joita oletetaan olevan korkeintaan yksi kappale
    * @param poistettava viite poistettavaan tietoalkioon
    * @return true, jos poisto onnistui, false ep�onnistuessa
    */
   @Override
   public Object poista(Object poistettava) {
      // k�yd��n lista l�pi
      for ( int i = 0; i < koko(); i++ ) {
         // jos alkio vastaa poistettavaa,
         if (alkio(i).equals(poistettava)) 
            // annetaan paluuarvona poistettu alkio
            return poista(i);
      }
      return null;   // jos poistettavaa ei l�ytynyt tai lista oli tyhj�
   }
}