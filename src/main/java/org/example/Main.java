package org.example;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Scanner;

public class Main {

    private static String TEXT = "description=001000--20-001-799803/9---           0,00--23/06/2023--85,00--     500.000,00--                                                                     ++, colorCode=2, id=200017998039001000, activation=A, title=D, tilde=0, productType=AC";
    public static void main(String[] args) {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("");

            TemporalAccessor date = formatter.parse("");


            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            simpleDateFormat.parse("20230107");

            SimpleDateFormat simpleDateFormatYear = new SimpleDateFormat("yyyy");
            String year = simpleDateFormatYear.format(date);
            System.out.println ("year:" + year);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }



























        //System.out.println("Hello world!");

        //Scanner scannerText = new Scanner(TEXT).useDelimiter(",");

        //if(scannerText.hasNext()){
            //String description = scannerText.next();
        /*    Scanner scannerDescription = new Scanner(TEXT).useDelimiter("=");
            if (scannerDescription.hasNext()){
                scannerDescription.next();
                if (scannerDescription.hasNext()){
                    String descriptionValue = scannerDescription.next();
                    Scanner scannerDescriptionValue = new Scanner(descriptionValue).useDelimiter("--");
                    while (scannerDescriptionValue.hasNext()) {
                        System.out.println(scannerDescriptionValue.next());
                    }
                }
            }
        //} */
    }
}