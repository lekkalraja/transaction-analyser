package com.hoolah.challenge;

import java.time.Instant;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ) {
        TransactionAnalyser transactionAnalyser = new TransactionAnalyser();
        if(args.length < 6){
            System.out.println("Please provide below details without missing \n fromDate[DD/MM/YYYY HH:MM:SS] \n toDate[DD/MM/YYYY HH:MM:SS] \n merchant[Kwik-E-Mart]");
            System.exit(1);
        }
        String csvFile = args[0];
        Instant fromDate = AppUtil.getInstant(args[1]+" "+args[2]);
        Instant toDate = AppUtil.getInstant(args[3]+" "+args[4]);
        if(fromDate == null || toDate == null){
            System.exit(1);
        }
        String merchant = args[5];
        boolean hasHeader = Boolean.parseBoolean(args[6]);
        Statistics statistics = transactionAnalyser.analyseTransactions(csvFile, fromDate, toDate, merchant, hasHeader);
        System.out.println("Number of transactions = "+statistics.getNumberOfTransactions());
        System.out.println("Average Transaction Value = "+AppUtil.round(statistics.getAverageTransactions()));
    }

}
