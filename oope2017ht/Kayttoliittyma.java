package oope2017ht;

/**
 * Käyttäjän ja tulkin välillä toimiva käyttöliittymä, sisältää harjoitustyön pääluupin
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017
 * <p>
 * @author Albert Iho (iho.albert.k@student.uta.fi)
 *
 */

import apulaiset.*;
import java.lang.*;
 
public class Kayttoliittyma {
   
   // vakioidaan tunnistettavat komennot ja annettava virheilmoitus
   public static final String LUOHAKEMISTO = "md";
   public static final String SIIRRY = "cd";
   public static final String LUOTIEDOSTO = "mf";
   public static final String NIMEAUUDELLEEN = "mv";
   public static final String LISTAASISALTO = "ls";
   public static final String KOPIOI = "cp";
   public static final String POISTA = "rm";
   public static final String TULOSTAKAIKKI = "find";
   public static final String LOPETA = "exit";
   public static final String VIRHEILMOITUS = "Error!";
   
   
   public void suorita() {
      boolean aja = true;
      // aktivoidaan tulkki
      Tulkki tulkki = new Tulkki();
      
      // pistetään työ pyörimään
      while(aja == true) {
         System.out.print(tulkki.annaPolku());
         String syote = In.readString();
         
         // muutetaan input käsiteltävään muotoon
         String[] katkottuSyote = syote.split(" ");
         
         // tarkistetaan sisältääkö paloitetltu syöte komentoja
         // hakemiston luonti
         if (katkottuSyote[0].equals(LUOHAKEMISTO)) {
            try {
               if (katkottuSyote.length == 2)
                  tulkki.luoHakemisto(katkottuSyote[1]);
               else
                  System.out.println(VIRHEILMOITUS);
            }
            catch (Exception e) {
               System.out.println(VIRHEILMOITUS);
            }
         }
         
         // hakemistossa siirtyminen
         else if (katkottuSyote[0].equals(SIIRRY)) {
               try {
                  if (katkottuSyote.length == 2)
                     tulkki.vaihdaHakemisto(katkottuSyote[1]);
                  else if (katkottuSyote.length == 1)
                     tulkki.vaihdaHakemisto();
                  else
                     System.out.println(VIRHEILMOITUS);
               }
               catch (Exception e) {
                  System.out.println(VIRHEILMOITUS);
               }
         }
         
         // tiedoston luominen
         else if (katkottuSyote[0].equals(LUOTIEDOSTO)) {
            try {
               if (katkottuSyote.length == 3) {
                  // luodaan käyttäjän antamasta koosta int-tyyppinen luku
                  int koko = Integer.parseInt(katkottuSyote[2]);
                  // kutsutaan tiedoston luovaa operaatiota käyttäjän antamilla arvoilla
                  tulkki.luoTiedosto(katkottuSyote[1], koko);
               }
               else
                  System.out.println(VIRHEILMOITUS);
            }
            catch (Exception e) {
               System.out.println(VIRHEILMOITUS);
            }
         }
         
         // nimen vaihto
         else if (katkottuSyote[0].equals(NIMEAUUDELLEEN)) {
            try {
               if (katkottuSyote.length == 3) {
                  // kutsutaan nimen vaihtavaa operaatiota käyttäjän antamilla arvoilla
                  tulkki.vaihdaNimi(katkottuSyote[1], katkottuSyote[2]);
               }
               else 
                  System.out.println(VIRHEILMOITUS);
            }
            catch (Exception e) {
               System.out.println(VIRHEILMOITUS);
            }
         }
         
         // listaaminen
         else if (katkottuSyote[0].equals(LISTAASISALTO)) {
            if (katkottuSyote.length == 2) {
               try {
                  tulkki.listaaHakemisto(katkottuSyote[1]);
               }
               catch (Exception e) {
                  System.out.println(VIRHEILMOITUS);
               }
            }
            else if (katkottuSyote.length == 1) {
               tulkki.listaaHakemisto();
            }
            else
               System.out.println(VIRHEILMOITUS);
         }
         
         // kopiointi
         else if (katkottuSyote[0].equals(KOPIOI)) {
            if (katkottuSyote.length == 3) {
               try {
                  tulkki.kopioiTiedosto(katkottuSyote[1], katkottuSyote[2]);
               }
               catch (Exception e) {
                  System.out.println(VIRHEILMOITUS);
               }
            }
            else
               System.out.println(VIRHEILMOITUS);
         }
         
         // kohteen poistaminen
         else if (katkottuSyote[0].equals(POISTA)) {
            if (katkottuSyote.length == 2) {
               try {
                  tulkki.poistaKohde(katkottuSyote[1]);
               }
               catch (Exception e) {
                  System.out.println(VIRHEILMOITUS);
               }
            }
            else
               System.out.println(VIRHEILMOITUS);
            
         }
         
         // rekursiivinen listaus
         else if (katkottuSyote[0].equals(TULOSTAKAIKKI)) {
            if (katkottuSyote.length == 1) {
               try {
                  tulkki.listaaRekursiivisesti();
               }
               catch (Exception e) {
                  System.out.println(VIRHEILMOITUS);
               }
            }
            else 
               System.out.println(VIRHEILMOITUS);
         }
         
         // pääluupin lopettaminen
         else if (katkottuSyote[0].equals(LOPETA) && katkottuSyote.length == 1) {
            aja = false;
         }
         
         // jos mitään komentoa ei tunnisteta syötteestä, annetaan virheilmoitus
         else
            System.out.println(VIRHEILMOITUS);
      }
   }
}