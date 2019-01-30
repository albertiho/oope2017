package oope2017ht;

/**
 * Harjoitusty�n konkreettista ty�t� tekev� luokka, suorittaa k�ytt�j�n k�skem�t komennot
 * <p>
 * Harjoitusty�, Olio-ohjelmoinnin perusteet, kev�t 2017
 * <p>
 * @author Albert Iho (iho.albert.k@student.uta.fi)
 *
 */

import oope2017ht.tiedot.*;
 
public class Tulkki {
   /*
    * Attribuutit
    *
    */
    
   // juurihakemisto
   private final Hakemisto JUURI;
   
   // k�ytt�j�n sijainti 
   private Hakemisto sijainti;
   
   /*
    * Rakentajat
    *
    */
   
   public Tulkki() {
      JUURI = new Hakemisto();
      sijainti = JUURI;
   }
   
   /*
    * Luokan omat metodit
    *
    */
   
   /** luodaan sijaintiin uusi hakemisto
    * @param nimi uuden hakemiston nimi
    * @throws IllegalArgumentException jos luominen ep�onnistuu
    */
   public void luoHakemisto(String nimi) throws IllegalArgumentException {
      // tutkitaan hakemiston sis�ll�st� samannimisi� tiedostoja
      if (sijainti.hae(nimi) == null) {
         try {
            // luodaan uusi hakemisto annetulla nimell�, ylihakemistoksi tulee nykyinen sijainti
            Hakemisto uusi = new Hakemisto(new StringBuilder(nimi), sijainti);
            
            // pakotetaan uusi hakemisto tieto-tyyppiseksi ett� voidaan k�ytt�� lis��-operaatiota
            Tieto apu = (Tieto)uusi;
            
            sijainti.lisaa(apu);
         }
         catch (Exception e) {
            throw new IllegalArgumentException();
         }
      }
      else // jos nykyisess� hakemistossa on jo sen niminen tiedosto tai hakemisto
         throw new IllegalArgumentException();
   }
   
   /** siirryt��n sijainnissa yli tai alihakemistoon
    * @param nimi siirrytt�v�n kohteen nimi
    * @throws IllegalArgumentException jos siirtyminen ei onnistu
    */
   public void vaihdaHakemisto (String nimi) throws IllegalArgumentException {
      // jos siirrytt�v�n kohteen nimi on .. ja sijainnilla on ylihakemisto
      if (nimi.equals("..") && sijainti.ylihakemisto() != null) { 
         sijainti = sijainti.ylihakemisto();
      }
      // jos siirrytt�v� kohde l�ytyy sijainnista
      else if (nimi != null && sijainti.hae(nimi) != null) {
         // pakotetaan hae-operaation palauttama Tieto-olio Hakemistoksi
         sijainti = (Hakemisto)sijainti.hae(nimi);
      }
      // valitetaan jos siirtyminen ep�onnistuu
      else 
         throw new IllegalArgumentException();
   }
   
   // apuoperaatio juureen siirtymiseen
   public void vaihdaHakemisto() {
      sijainti = JUURI;
   }
   
   /** luodaan uusi tiedosto jos mahdollista
    * @param nimi uuden tiedoston nimi
    * @param koko uuden tiedoston koko
    * @throws IllegalArgumentException jos luonti ep�onnistuu
    */
   public void luoTiedosto(String nimi, int koko) throws IllegalArgumentException {
      // jos sen nimist� tiedostoa ei ole jo olemassa
      if (sijainti.hae(nimi) == null) {
         try {
            Tiedosto uusi = new Tiedosto(new StringBuilder(nimi), koko);
            
            // pakotetaan tiedosto tiedoksi
            Tieto apu = (Tieto)uusi;
            
            // lis�t��n tiedosto nykyhakemistoon
            sijainti.lisaa(apu);
         }
         catch (Exception e) {
            throw new IllegalArgumentException();
         }
      }
      else // jos samanniminen tiedosto on jo olemassa
         throw new IllegalArgumentException();
   }
   
   /** vaihdetaan tiedoston nimi
    * @param nimi muutettava tiedosto
    * @param uusi muutettavan uusi nimi
    * @throws IllegalArgumentException jos vaihto ei onnistu
    */
   public void vaihdaNimi(String nimi, String uusi) throws IllegalArgumentException {
      // tarkistetaan ett� kohde on olemassa ja uusi nimi on vapaa
      if(nimi != null && uusi != null && 
         sijainti.hae(nimi) != null && sijainti.hae(uusi) == null) {
         // otetaan kohde haltuun poistamalla se sijainnista
         Tieto kohde = sijainti.poista(nimi);
         
         // vaihdetaan kohteen nimi
         kohde.nimi(new StringBuilder(uusi));
         
         // k�ytet��n lis��-operaatiota aakkosj�rjestyksen s�ilytt�miseksi
         sijainti.lisaa(kohde);
      }
      else // jos uusi nimi on jo olemassa tai kohdetta ei l�ydy
         throw new IllegalArgumentException();
   }
   
   // operaatio sijainnin sis�ll�n tulostamiseksi
   public void listaaHakemisto() {
      // k�yd��n hakemisto l�pi
      for(int i = 0; i < sijainti.hakemistonSisalto().koko(); i++)
         // ja tulostetaan hakemiston sis�lt�
         System.out.println(sijainti.hakemistonSisalto().alkio(i).toString());
   }
   
   /** operaatio yksitt�isen kohteen listaamiseksi
    * @param nimi listattava kohde
    * @throws IllegalArgumentException jos listaus ei onnistu
    */
   public void listaaHakemisto(String nimi) {
      // jos listattava kohde on olemassa
      if(sijainti.hae(nimi) != null) {
         // listataan kohde
         System.out.println(sijainti.hae(nimi).toString());
      }
      else 
         throw new IllegalArgumentException();
   }
  
   /** operaatio rekursiiviseen listaamiseen, listaa sijainnin ja kaikki sen alihakemistot
    * kapseloinnin vuoksi private, operaatiota kutsutaan apuoperaatiolla
    * @param h listattava hakemisto
    */
   private void listaaRekursiivisesti(Hakemisto h) {
      // kutsutaan apuoperaatiota 
      String polku = annaPolku(h);
      
      //poistetaan polusta viimeinen merkki
      polku = polku.substring(0, polku.length()-1);
      
      // k�yd��n l�pi sijainti ja kaikki alihakemistot
      for (int i = 0; i < h.hakemistonSisalto().koko(); i++) {
         Object apu = h.hakemistonSisalto().alkio(i);
         
         // tulostetaan alkio
         System.out.println(polku + apu.toString());
         
         // jos alkio oli hakemisto, tulostetaan sen sis�lt� rekursiivisesti
         if (apu instanceof Hakemisto) {
            Hakemisto apuHakemisto = (Hakemisto)apu;
            listaaRekursiivisesti(apuHakemisto);
         }
      }
   }
   
   // operaatio jota kutsuttaan k�ytt�liittym�st�
   public void listaaRekursiivisesti() {
      // kutsutaan listaavaa operaatiota antamalla nykyinen sijainti parametriksi
      listaaRekursiivisesti(sijainti);
   }
   
   /** tiedoston kopioiva operaatio
    * @param kopioitava kopioitavan tiedoston nimi
    * @param nimi uuden kopion nimi
    * @throws IllegalArgumentException jos kopiointi ep�onnistuu
    */
   public void kopioiTiedosto(String kopioitava, String nimi) throws IllegalArgumentException {
      if (sijainti.hae(kopioitava) != null && sijainti.hae(nimi) == null) {
         try {
            // haetaan kopioitava kohde k�sittelyyn ja muutetaan se Tiedosto-olioksi
            Tiedosto apu = (Tiedosto)sijainti.hae(kopioitava);
            
            // kutsutaan tiedoston syv�kopioivaa kopiorakentajaa
            Tiedosto uusi = new Tiedosto(apu);
            
            // annetaan kopioidulle tiedostolle uusi nimi
            uusi.nimi(new StringBuilder (nimi));
            
            // lis�t��n kopioitu tiedosto kohteeseen, pakotetaan kopioitu tiedosto Tieto-olioksi
            sijainti.lisaa((Tieto)uusi);
         }
         catch (Exception e) {
            throw new IllegalArgumentException();
         }
      }
      else
         throw new IllegalArgumentException();
   }
   
   /** operaatio joka poistaa sijainnista tiedoston
    * @param nimi poistettavan kohteen nimi
    * @throws IllegalArgumentException jos poisto ei onnistu
    */
   public void poistaKohde(String nimi) throws IllegalArgumentException {
      // valitetaan jos kohteen poisto ei onnistu
      if (sijainti.poista(nimi) == null)
         throw new IllegalArgumentException();
   }
   
   /** operaatio polun n�ytt�miseen
    * @param h hakemisto josta polku alkaa
    * @return String, joka sis�lt�� polun sijaintiin
    */
   private String annaPolku(Hakemisto h) {
      String polku = ">";
      
      // siirryt��n sijainnista kohti juurta
      while (h != null) {
         StringBuilder apu = h.nimi();
         
         // jos ylihakemisto on null (sijainti on juuressa)
         if (h.ylihakemisto() == null)
            polku = apu + "" + polku;
         else
            polku = apu + "/" + polku;
         
         // siirryt��n ylihakemistoon seuraavaa kierrosta varten
         h = h.ylihakemisto();
      }
      return polku;
   }
   
   /** apumetodi jota k�ytt�liittym� kutsuu
    * @return String, jossa on polku sijaintiin
    */
   public String annaPolku() {
      return annaPolku(sijainti);
   }
}