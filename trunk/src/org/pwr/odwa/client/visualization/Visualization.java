package org.pwr.odwa.client.visualization;


import org.pwr.odwa.common.result.DBResult;

/**
 * Klasa odpowiadajaca za wyswietlanie danych uzytkownikowi
 *
 * @author Wojciech Skórski
 * @author Paweł Szołtysek
 *
 */
public class Visualization
{

   /**
    * Metoda wyswietla raport z zadanymi stylami.
    *
    * @param result
    *           dane, które mają zostać wyświetlone,
    * @param style
    *           style, zgodnie z którymi mają zostać wyświetlone dane.
    *
    */
   public void show(DBResult result, ReportStyle style)
   {
      System.out.println("ODWAClient: Visualization: show executed");
      // System.out.println("Wyświetlono raport rozszerzony");
   }

   /**
    * Metoda zwracająca obecnie aktywny styl.
    *
    * @return obecnie aktywny styl raportu
    *
    */
   public ReportStyle getReportStyle()
   {
      System.out.println("ODWAClient: Visualization: getReportStyle executed");
      ReportStyle reportStyle = new ReportStyle();
      return reportStyle;
   }

}
