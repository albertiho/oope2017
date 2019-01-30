package oope2017ht.omalista;

/**
 * LinkitettyLista-luokan perivä OmaLista luokka
 * Toimii hakemistojen selkärankana ja toteuttaa Ooperoiva rajapinnan
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017
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
    * alkioiden monistumista ei tarvitse käsitellä sillä kyseisessä ympäristössä
    * samanlaisten alkioiden luominen mahdotonta.
    * @param haettava listalta etsittävä tieto
    * @return viite löydettyyn alkioon, paluuarvo null jos haku epäonnistuu
    */
   @Override
   public Object hae(Object haettava) {
      // käydään lista läpi ja etsitään haettavaa
      for ( int i = 0; i < koko(); i++ ) {
         if (alkio(i).equals(haettava))
            // haettavan löytyessä palautetaan löydetty alkio
            return alkio(i);
      }
      return null;
   }
   
   /** lisää alkion listalle oikeaan paikkaan.
    * oikea paikka = aakkosjärjestyksen mukaan.
    * @param uusi viite lisättäävään olioon
    * @return true, jos lisäys onnistuu, false epäonnistuessa
    */
   @SuppressWarnings("unchecked")
   @Override
   public boolean lisaa(Object uusi) {
      // jos uusi ei ole null, eikä sitä löydy listalta
      if (uusi != null && hae(uusi) == null) {
   
         try { // yritetään lisätä alkio listaan
            // asetetaan vertailtaviin olioihin comparable-rajapinnan viite
            Comparable uudempi = (Comparable)uusi;
            if (koko < 1) {  // jos lista on tyhjä, lisätään alkio alkuun
               lisaaAlkuun(uusi);
               return true;
               
            // kun listalla on muita alkioita, käydään lista läpi
            }else { 
               for ( int i = 0; i <= koko; i++ ) {
                  // jos koko lista on käyty läpi löytämättä suurempaa alkiota
                  if (i == koko) {
                     lisaaLoppuun(uusi);  // lisätään suurin alkio loppuun
                     return true;   // paluuarvona totuus  
                     
                  // jos listalla oleva arvo on suurempi kuin lisättävä arvo                  
                  }else if (uudempi.compareTo(alkio(i)) < 0) {
                     lisaa(i, uusi);   // sijoitetaan alkio listalle oikeaan paikkaan
                     return true;   // annetaan tosi paluuarvona onnistumisesta
                  }
               }
            }
         }
         catch (Exception e) {   // lisäämisen epäonnistuessa annetaan paluuarvona false
            return false;
         }
      }
      return false;
   }
   
   /** poistaa listalta oliota equals-mielessä vastaavan alkion,
    * joita oletetaan olevan korkeintaan yksi kappale
    * @param poistettava viite poistettavaan tietoalkioon
    * @return true, jos poisto onnistui, false epäonnistuessa
    */
   @Override
   public Object poista(Object poistettava) {
      // käydään lista läpi
      for ( int i = 0; i < koko(); i++ ) {
         // jos alkio vastaa poistettavaa,
         if (alkio(i).equals(poistettava)) 
            // annetaan paluuarvona poistettu alkio
            return poista(i);
      }
      return null;   // jos poistettavaa ei löytynyt tai lista oli tyhjä
   }
}