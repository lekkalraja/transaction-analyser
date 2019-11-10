package com.hoolah.challenge;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;

public class AppUtil {

    private static SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static Instant getInstant(String date){
        try {
            return dataFormat.parse(date).toInstant();
        } catch (ParseException e) {
            System.err.println("Please provide "+date+"in [DD/MM/YYYY HH:MM:SS] format");
        }
        return null;
    }

    public static double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
