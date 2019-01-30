package oope2017ht;

/**
 * Harjoitustyön konkreettista työtä tekevä luokka, suorittaa käyttäjän käskemät komennot
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017
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
   
   // käyttäjän sijainti 
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
    * @throws IllegalArgumentException jos luominen epäonnistuu
    */
   public void luoHakemisto(String nimi) throws IllegalArgumentException {
      // tutkitaan hakemiston sisällöstä samannimisiä tiedostoja
      if (sijainti.hae(nimi) == null) {
         try {
            // luodaan uusi hakemisto annetulla nimellä, ylihakemistoksi tulee nykyinen sijainti
            Hakemisto uusi = new Hakemisto(new StringBuilder(nimi), sijainti);
            
            // pakotetaan uusi hakemisto tieto-tyyppiseksi että voidaan käyttää lisää-operaatiota
            Tieto apu = (Tieto)uusi;
            
            sijainti.lisaa(apu);
         }
         catch (Exception e) {
            throw new IllegalArgumentException();
         }
      }
      else // jos nykyisessä hakemistossa on jo sen niminen tiedosto tai hakemisto
         throw new IllegalArgumentException();
   }
   
   /** siirrytään sijainnissa yli tai alihakemistoon
    * @param nimi siirryttävän kohteen nimi
    * @throws IllegalArgumentException jos siirtyminen ei onnistu
    */
   public void vaihdaHakemisto (String nimi) throws IllegalArgumentException {
      // jos siirryttävän kohteen nimi on .. ja sijainnilla on ylihakemisto
      if (nimi.equals("..") && sijainti.ylihakemisto() != null) { 
         sijainti = sijainti.ylihakemisto();
      }
      // jos siirryttävä kohde löytyy sijainnista
      else if (nimi != null && sijainti.hae(nimi) != null) {
         // pakotetaan hae-operaation palauttama Tieto-olio Hakemistoksi
         sijainti = (Hakemisto)sijainti.hae(nimi);
      }
      // valitetaan jos siirtyminen epäonnistuu
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
    * @throws IllegalArgumentException jos luonti epäonnistuu
    */
   public void luoTiedosto(String nimi, int koko) throws IllegalArgumentException {
      // jos sen nimistä tiedostoa ei ole jo olemassa
      if (sijainti.hae(nimi) == null) {
         try {
            Tiedosto uusi = new Tiedosto(new StringBuilder(nimi), koko);
            
            // pakotetaan tiedosto tiedoksi
            Tieto apu = (Tieto)uusi;
            
            // lisätään tiedosto nykyhakemistoon
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
      // tarkistetaan että kohde on olemassa ja uusi nimi on vapaa
      if(nimi != null && uusi != null && 
         sijainti.hae(nimi) != null && sijainti.hae(uusi) == null) {
         // otetaan kohde haltuun poistamalla se sijainnista
         Tieto kohde = sijainti.poista(nimi);
         
         // vaihdetaan kohteen nimi
         kohde.nimi(new StringBuilder(uusi));
         
         // käytetään lisää-operaatiota aakkosjärjestyksen säilyttämiseksi
         sijainti.lisaa(kohde);
      }
      else // jos uusi nimi on jo olemassa tai kohdetta ei löydy
         throw new IllegalArgumentException();
   }
   
   // operaatio sijainnin sisällön tulostamiseksi
   public void listaaHakemisto() {
      // käydään hakemisto läpi
      for(int i = 0; i < sijainti.hakemistonSisalto().koko(); i++)
         // ja tulostetaan hakemiston sisältö
         System.out.println(sijainti.hakemistonSisalto().alkio(i).toString());
   }
   
   /** operaatio yksittäisen kohteen listaamiseksi
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
      
      // käydään läpi sijainti ja kaikki alihakemistot
      for (int i = 0; i < h.hakemistonSisalto().koko(); i++) {
         Object apu = h.hakemistonSisalto().alkio(i);
         
         // tulostetaan alkio
         System.out.println(polku + apu.toString());
         
         // jos alkio oli hakemisto, tulostetaan sen sisältö rekursiivisesti
         if (apu instanceof Hakemisto) {
            Hakemisto apuHakemisto = (Hakemisto)apu;
            listaaRekursiivisesti(apuHakemisto);
         }
      }
   }
   
   // operaatio jota kutsuttaan käyttöliittymästä
   public void listaaRekursiivisesti() {
      // kutsutaan listaavaa operaatiota antamalla nykyinen sijainti parametriksi
      listaaRekursiivisesti(sijainti);
   }
   
   /** tiedoston kopioiva operaatio
    * @param kopioitava kopioitavan tiedoston nimi
    * @param nimi uuden kopion nimi
    * @throws IllegalArgumentException jos kopiointi epäonnistuu
    */
   public void kopioiTiedosto(String kopioitava, String nimi) throws IllegalArgumentException {
      if (sijainti.hae(kopioitava) != null && sijainti.hae(nimi) == null) {
         try {
            // haetaan kopioitava kohde käsittelyyn ja muutetaan se Tiedosto-olioksi
            Tiedosto apu = (Tiedosto)sijainti.hae(kopioitava);
            
            // kutsutaan tiedoston syväkopioivaa kopiorakentajaa
            Tiedosto uusi = new Tiedosto(apu);
            
            // annetaan kopioidulle tiedostolle uusi nimi
            uusi.nimi(new StringBuilder (nimi));
            
            // lisätään kopioitu tiedosto kohteeseen, pakotetaan kopioitu tiedosto Tieto-olioksi
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
   
   /** operaatio polun näyttämiseen
    * @param h hakemisto josta polku alkaa
    * @return String, joka sisältää polun sijaintiin
    */
   private String annaPolku(Hakemisto h) {
      String polku = ">";
      
      // siirrytään sijainnista kohti juurta
      while (h != null) {
         StringBuilder apu = h.nimi();
         
         // jos ylihakemisto on null (sijainti on juuressa)
         if (h.ylihakemisto() == null)
            polku = apu + "" + polku;
         else
            polku = apu + "/" + polku;
         
         // siirrytään ylihakemistoon seuraavaa kierrosta varten
         h = h.ylihakemisto();
      }
      return polku;
   }
   
   /** apumetodi jota käyttöliittymä kutsuu
    * @return String, jossa on polku sijaintiin
    */
   public String annaPolku() {
      return annaPolku(sijainti);
   }
}