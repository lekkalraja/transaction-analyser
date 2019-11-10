package com.hoolah.challenge;

import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;

public class TransactionAnalyserTest {

    @Test
    public void shouldAnalyseTransactions(){
        TransactionAnalyser transactionAnalyser = new TransactionAnalyser();
        String csvFile = "/home/raja/IdeaProjects/transactionanalyser/src/test/resources/transactions.csv";
        Instant fromDate = AppUtil.getInstant("20/08/2018 12:00:00");
        Instant toDate = AppUtil.getInstant("20/08/2018 13:00:00");
        Boolean hasHeader = true;
        String merchant = "Kwik-E-Mart";
        Statistics statistics = transactionAnalyser.analyseTransactions(csvFile, fromDate, toDate, merchant, hasHeader);
        Assert.assertEquals(2, statistics.getNumberOfTransactions());
        Assert.assertEquals(35.47, AppUtil.round(statistics.getAverageTransactions()),0);
    }

    @Test
    public void shouldAnalyseTransactionsNegative(){
        TransactionAnalyser transactionAnalyser = new TransactionAnalyser();
        String csvFile = "/home/raja/IdeaProjects/transactionanalyser/src/test/resources/transactions.csv";
        Instant fromDate = AppUtil.getInstant("20/09/2018 12:00:00");
        Instant toDate = AppUtil.getInstant("20/09/2018 13:00:00");
        Boolean hasHeader = true;
        String merchant = "Kwik-E-Mart";
        Statistics statistics = transactionAnalyser.analyseTransactions(csvFile, fromDate, toDate, merchant, hasHeader);
        Assert.assertEquals(0, statistics.getNumberOfTransactions());
        Assert.assertEquals(0, AppUtil.round(statistics.getAverageTransactions()),0);
    }
}
